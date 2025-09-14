package com.example.techburg

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.example.techburg.databinding.FragmentProductBinding

class ProductFragment : Fragment() {
    private val arg by navArgs<ProductFragmentArgs>()
    private var _binding: FragmentProductBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "null"
        }

    private val dataBaseProduct: RoomProductDao by lazy{
        requireContext()
            .appDatabase
            .productDao()
    }

    private val dataBaseCart: RoomCartDao by lazy{
        requireContext()
            .appDatabase
            .cartDao()
    }

    private val dataBaseViewed: RoomViewedDao by lazy{
        requireContext()
            .appDatabase
            .viewedDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentProductBinding.inflate(inflater,container,false)
            .also{
                _binding = it
            }.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run{
            val history = dataBaseViewed.getByUserId(RegistrationFragment.ID.toInt())
            if (history.size >= 2) {
                dataBaseViewed.delete(history[0])
            }
            dataBaseViewed.insertAll(
                Roomviewed(
                    userId = RegistrationFragment.ID.toInt(),
                    view = arg.name
                )
            )
            var col = "Silver"
            var cap = "256 gb"
            back.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            val product = dataBaseProduct.getByName(arg.name)
            var adapter = ImageAdapter(
                product.image.split(" ").map {
                    it.toInt()
                }.toMutableList()
            )
            indicator.isVisible = true
            viewPager.adapter = adapter
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            indicator.setViewPager(viewPager)
            name.text = product.name
            price.text = "USD ${product.price}"
            if (product.name != "iPhone 11 Pro"){
                color.isVisible = false
                capacity.isVisible = false
                color1.isVisible = false
                color2.isVisible = false
                color3.isVisible = false
                color4.isVisible = false
                cap1.isVisible = false
                cap2.isVisible = false
                cap3.isVisible = false
            }
            color1.setOnClickListener {
                color1.setImageResource(R.drawable.color1_selected)
                color2.setImageResource(R.drawable.color2)
                color3.setImageResource(R.drawable.color3)
                color4.setImageResource(R.drawable.color4)
                col = "Space gray"
                adapter = ImageAdapter(
                    mutableListOf(
                        R.mipmap.iphone_11_pro_1_space_gray,
                        R.mipmap.iphone_11_pro_2_space_gray
                    )
                )
                viewPager.adapter = adapter
                indicator.setViewPager(viewPager)
            }
            color2.setOnClickListener {
                color1.setImageResource(R.drawable.color1)
                color2.setImageResource(R.drawable.color2_selected)
                color3.setImageResource(R.drawable.color3)
                color4.setImageResource(R.drawable.color4)
                col = "Silver"
                adapter = ImageAdapter(
                    mutableListOf(
                        R.mipmap.iphone_11_pro_1_silver,
                        R.mipmap.iphone_11_pro_2_silver
                    )
                )
                viewPager.adapter = adapter
                indicator.setViewPager(viewPager)
            }
            color3.setOnClickListener {
                color1.setImageResource(R.drawable.color1)
                color2.setImageResource(R.drawable.color2)
                color3.setImageResource(R.drawable.color3_selected)
                color4.setImageResource(R.drawable.color4)
                col = "Night green"
                adapter = ImageAdapter(
                    mutableListOf(
                        R.mipmap.iphone_11_pro_1_night_green,
                        R.mipmap.iphone_11_pro_2_night_green
                    )
                )
                viewPager.adapter = adapter
                indicator.setViewPager(viewPager)
            }
            color4.setOnClickListener {
                color1.setImageResource(R.drawable.color1)
                color2.setImageResource(R.drawable.color2)
                color3.setImageResource(R.drawable.color3)
                color4.setImageResource(R.drawable.color4_selected)
                col = "Gold"
                adapter = ImageAdapter(
                    mutableListOf(
                        R.mipmap.iphone_11_pro_1_gold,
                        R.mipmap.iphone_11_pro_2_gold
                    )
                )
                viewPager.adapter = adapter
                indicator.setViewPager(viewPager)
            }
            cap1.setOnClickListener {
                cap1.setTextColor(Color.parseColor("#0001fc"))
                cap2.setTextColor(R.color.gray)
                cap3.setTextColor(R.color.gray)
                cap = "64 gb"
            }
            cap2.setOnClickListener {
                cap1.setTextColor(R.color.gray)
                cap2.setTextColor(Color.parseColor("#0001fc"))
                cap3.setTextColor(R.color.gray)
                cap = "256 gb"
            }
            cap3.setOnClickListener {
                cap1.setTextColor(R.color.gray)
                cap2.setTextColor(R.color.gray)
                cap3.setTextColor(Color.parseColor("#0001fc"))
                cap = "512 gb"
            }
            ///
            btn.setOnClickListener {
                if (name.text == "iPhone 11 Pro") {
                    dataBaseCart.insertAll(
                        Roomcart(
                            name = product.name,
                            userId = RegistrationFragment.ID.toInt(),
                            color = col,
                            capacity = cap,
                            image = product.image.split(" ")[0].toInt(),
                            price = product.price,
                            sale = product.sale
                        )
                    )
                }
                else{
                    dataBaseCart.insertAll(
                        Roomcart(
                            name = product.name,
                            userId = RegistrationFragment.ID.toInt(),
                            color = null,
                            capacity = null,
                            image = product.image.split(" ")[0].toInt(),
                            price = product.price,
                            sale = product.sale
                        )
                    )
                }
                btn.isVisible = false
                btnInactive.isVisible = true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}