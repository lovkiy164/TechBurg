package com.example.techburg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.techburg.databinding.FragmentPage1Binding
import com.google.android.material.tabs.TabLayoutMediator

class PageFragment1 : Fragment() {
    private var listOfProducts: Array<Roomproduct>? = null
    private var list: Array<Roomproductrec>? = null
    private var listOfSales: Array<Roomsale>? = null
    private var adapter: SalesAdapter? = null
    private var rec_adapter: PageAdapter? = null
    private var _binding: FragmentPage1Binding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "null"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentPage1Binding.inflate(inflater,container,false)
            .also{
                _binding = it
            }.root
    }

    private val dataBaseSale: RoomSaleDao by lazy{
        requireContext()
            .appDatabase
            .saleDao()
    }

    private val dataBaseProductRec: RoomProductRecDao by lazy{
        requireContext()
            .appDatabase
            .productRecDao()
    }

    private val dataBaseProduct: RoomProductDao by lazy{
        requireContext()
            .appDatabase
            .productDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run{
            if (dataBaseProduct.getAll().isEmpty()){
                listOfProducts = arrayOf(
                    Roomproduct(image = "${R.mipmap.phone}", category = "Smartphones", sale = 50, name = "iPhone 15", price = 449, capacity = null, color = null),
                    Roomproduct(image = "${R.mipmap.rec3}", category = "Smartphones", sale = 50, name = "Samsung Galaxy S23", price = 549, capacity = null, color = null),
                    Roomproduct(image = "${R.mipmap.rec2}", category = "Laptops", sale = 40, name = "MacBook Air M3", price = 999, capacity = null, color = null),
                    Roomproduct(image = "${R.mipmap.iphone_11_pro_1_silver} ${R.mipmap.iphone_11_pro_2_silver}", category = "Smartphones", sale = 50, name = "iPhone 11 Pro", price = 425, capacity = "256 gb", color = "silver"),
                    Roomproduct(image = "${R.mipmap.rec4}", category = "Consoles", sale = 20, name = "PlayStation 5", price = 399, capacity = null, color = null),
                    Roomproduct(image = "${R.mipmap.xbox_series_x}", category = "Consoles", sale = 20, name = "Xbox Series X", price = 399, capacity = null, color = null),
                    Roomproduct(image = "${R.mipmap.rec1}", category = "Household", sale = 0, name = "Bose Home Speaker", price = 279, capacity = null, color = null),
                    Roomproduct(image = "${R.mipmap.iphone_14_pro_1} ${R.mipmap.iphone_14_pro_2}", category = "Smartphones", sale = 50, name = "iPhone 14 Pro", price = 399, capacity = null, color = null))
                dataBaseProduct.insertAll(*listOfProducts!!)
            }
            if(dataBaseSale.getAll().isEmpty()) {
                listOfSales = arrayOf(
                    Roomsale(image = R.mipmap.phone, category = "Smartphones", sale = 50),
                    Roomsale(image = R.mipmap.rec2, category = "Laptops", sale = 40),
                    Roomsale(image = R.mipmap.rec4, category = "Consoles", sale = 20)
                )
                dataBaseSale.insertAll(*listOfSales!!)
            }
            adapter = SalesAdapter(dataBaseSale.getAll().toMutableList()) {
                findNavController().navigate(
                    PageFragment1Directions.actionFragment1ToFragmentSale(it.category)
                )
            }
            if (dataBaseProductRec.getAll().isEmpty()){
                list = arrayOf(
                    Roomproductrec(image = R.mipmap.rec1, category = "Household", sale = 0, name = "Bose Home Speaker", price = 279),
                    Roomproductrec(image = R.mipmap.rec2, category = "Laptops", sale = 40, name = "MacBook Air M3", price = 999),
                    Roomproductrec(image = R.mipmap.rec3, category = "Smartphones", sale = 50, name = "Samsung Galaxy S23", price = 549),
                    Roomproductrec(image = R.mipmap.rec4, category = "Consoles", sale = 20, name = "PlayStation 5", price = 399)
                )
                dataBaseProductRec.insertAll(*list!!)
            }
            rec_adapter = PageAdapter(
                dataBaseProductRec.getAll().toMutableList()
            ){
                findNavController().navigate(
                    PageFragment1Directions.actionFragment1ToFragmentProduct(it.name)
                )
            }
            indicator.isVisible = true
            viewPager.adapter = rec_adapter
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL,false)
            indicator.setViewPager(viewPager)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}