package com.example.techburg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techburg.databinding.FragmentSaleBinding

class SaleFragment : Fragment() {
    private var adapter: ProductSalesAdapter? = null
    private val args by navArgs<SaleFragmentArgs>()
    private var _binding: FragmentSaleBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "null"
        }

    private val dataBaseSale: RoomSaleDao by lazy{
        requireContext()
            .appDatabase
            .saleDao()
    }

    private val dataBaseProduct: RoomProductDao by lazy{
        requireContext()
            .appDatabase
            .productDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSaleBinding.inflate(inflater,container,false)
            .also{
                _binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run{
            val list = dataBaseProduct.getByCategory(args.category)
            sales.text = args.category
            adapter = ProductSalesAdapter(list.toMutableList()) {
                findNavController().navigate(
                    SaleFragmentDirections.actionFragmentSaleToFragmentProduct(it.name)
                )
            }
            recyclerView.adapter = adapter
            back.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            recyclerView.layoutManager =
                GridLayoutManager(view.context, 2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}