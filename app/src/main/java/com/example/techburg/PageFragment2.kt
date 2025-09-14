package com.example.techburg

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techburg.databinding.FragmentPage2Binding
import java.util.Timer
import java.util.TimerTask

class PageFragment2 : Fragment() {
    private var listOfProducts: Array<Roomproduct>? = null
    private lateinit var itemsProducts: MutableList<Roomproduct>
    private var viewedAdapter: LastViewedHistoryAdapter? = null
    private var historyAdapter: HistoryAdapter? = null
    private lateinit  var adapterAutoComplete: AutoCompleteAdapter
    private var _binding: FragmentPage2Binding? = null
    private lateinit var timer: Timer
    private val binding
        get() = requireNotNull(_binding){
            "null"
        }

    private val dataBaseProduct: RoomProductDao by lazy{
        requireContext()
            .appDatabase
            .productDao()
    }

    private val dataBaseHistory: RoomHistoryDao by lazy{
        requireContext()
            .appDatabase
            .historyDao()
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
        return FragmentPage2Binding.inflate(inflater,container,false)
            .also{
                _binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run{
            val v = mutableListOf<Roomproduct>()
            dataBaseViewed.getByUserId(RegistrationFragment.ID.toInt()).forEach {
                v.add(dataBaseProduct.getByName(it.view))
            }
            viewedAdapter = LastViewedHistoryAdapter(v){
                findNavController().navigate(
                    PageFragment2Directions.actionFragment2ToFragmentProduct(it.name)
                )
            }
            var h = mutableListOf<String>()
            dataBaseHistory.getByUserId(RegistrationFragment.ID.toInt()).forEach {
                h.add(it.request)
            }
            historyAdapter = HistoryAdapter(h){
                pos, it ->
                historyAdapter?.delete(pos)
                val item = dataBaseHistory.getByQuery(it)
                dataBaseHistory.delete(item)
            }
            recyclerView.adapter = viewedAdapter
            recyclerView.layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)
            history.adapter = historyAdapter
            history.layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)
            clean.setOnClickListener {
                Log.d("History","deleted")

                dataBaseHistory.getByUserId(RegistrationFragment.ID.toInt()).forEach{
                    historyAdapter?.delete(0)
                    dataBaseHistory.delete(it)
                }
            }
            ////
            itemsProducts = dataBaseProduct.getAll().toMutableList()
            adapterAutoComplete = AutoCompleteAdapter(requireContext(),itemsProducts)
            actvSearch.setAdapter(adapterAutoComplete)
            actvSearch.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (actvSearch.isPerformingCompletion) {
                        return
                    } else {
                        binding.pbSearch.visibility = View.VISIBLE
                        timer = Timer()
                        timer.schedule(object : TimerTask() {
                            override fun run() {
                                if (!s.isNullOrBlank()) {
                                    activity?.runOnUiThread {
                                        getDataProductsList(s.toString())
                                    }
                                } else {
                                    activity?.runOnUiThread {
                                        binding.pbSearch.visibility = View.GONE
                                    }
                                }
                            }
                        }, 500)
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    Log.d("beforeTextChanged", "yes")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Log.d("onTextChanged", "yes")
                    if (::timer.isInitialized) {
                        timer.cancel()
                    }
                }
            })

            actvSearch.setOnItemClickListener{
                    parent,_,position,_ ->
                val selectedItem = parent.adapter.getItem(position) as Roomproduct
                actvSearch.setText(selectedItem.name,false)
                hideKeyboard()
                val history = dataBaseHistory.getByUserId(RegistrationFragment.ID.toInt())
                if (history.size >= 5) {
                    dataBaseHistory.delete(history[0])
                }
                dataBaseHistory.insertAll(
                    Roomhistory(
                        userId = RegistrationFragment.ID.toInt(),
                        request = actvSearch.text.toString()
                    )
                )
                actvSearch.clearFocus()
                findNavController().navigate(
                    PageFragment2Directions.actionFragment2ToFragmentProduct(selectedItem.name)
                )
            }
        }
    }

    private fun getDataProductsList(keyword: String){
        itemsProducts = dataBaseProduct.getAll().toMutableList()
        val filteredList = itemsProducts.filter{
            it.name.contains(keyword, ignoreCase = true)
        }
        itemsProducts.clear()
        itemsProducts.addAll(filteredList)
        adapterAutoComplete.filterList(filteredList.toMutableList())
        binding.pbSearch.visibility = View.GONE
    }

    private fun hideKeyboard(){
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}