package com.example.techburg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techburg.databinding.FragmentPage3Binding

class PageFragment3 : Fragment() {
    private var adapter: CheckoutAdapter? = null
    private var _binding: FragmentPage3Binding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "null"
        }

    private val dataBaseCart: RoomCartDao by lazy{
        requireContext()
            .appDatabase
            .cartDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentPage3Binding.inflate(inflater,container,false)
            .also{
                _binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run{
            var cart = dataBaseCart.getByUserId(RegistrationFragment.ID.toInt())
            adapter = CheckoutAdapter(
                dataBaseCart.getByUserId(RegistrationFragment.ID.toInt()).toMutableList()
            ){
                it, item ->
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL,false)
                adapter?.removeAt(it)
                dataBaseCart.delete(item)
                cart = dataBaseCart.getByUserId(RegistrationFragment.ID.toInt())
                var all = 0
                var amount = 0
                cart.forEach {
                    all++
                    amount += it.price
                }
                if (all != 1) {
                    text1.text = "$all items in cart"
                }
                else{
                    text1.text = "$all item in cart"
                }
                text4.text = "USD $amount"
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL,false)
            var all = 0
            var amount = 0
            cart.forEach {
                all++
                amount += it.price
            }
            if (all != 1) {
                text1.text = "$all items in cart"
            }
            else{
                text1.text = "$all item in cart"
            }
            text4.text = "USD $amount"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}