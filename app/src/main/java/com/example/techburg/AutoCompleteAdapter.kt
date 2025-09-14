package com.example.techburg

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.techburg.databinding.ItemSearchBinding

class AutoCompleteAdapter(
    context: Context,
    itemsList: MutableList<Roomproduct>
) : ArrayAdapter<Roomproduct>(context,0,itemsList){
    private var itemsListFull: MutableList<Roomproduct> = itemsList
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val binding: ItemSearchBinding
        val view: View
        binding = ItemSearchBinding.inflate(inflater,parent,false)
        view = binding.root
        val item = getItem(position)
        if (item != null){
            binding.name.text = item.name
            binding.image.setImageResource(item.image.split(" ")[0].toInt())
            binding.price.text = "USD ${item.price}"
        }
        return view
    }

    fun filterList(filteredList: MutableList<Roomproduct>){
        itemsListFull = filteredList
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return itemsListFull.size
    }

    override fun getItem(position: Int): Roomproduct?{
        return if (position >= 0 && position < itemsListFull.size){
            itemsListFull[position]
        }
        else{
            null
        }
    }
}