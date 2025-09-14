package com.example.techburg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.techburg.databinding.ItemSalesBinding

class SalesAdapter(
    private var items: MutableList<Roomsale>,
    private val itemClick: (Roomsale) -> Unit
) : RecyclerView.Adapter<SalesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SalesViewHolder(
            binding = ItemSalesBinding.inflate(layoutInflater,parent,false), itemClick
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun filterList(filteredList: ArrayList<Roomsale>){
        items = filteredList
        notifyDataSetChanged()
    }
}

class SalesViewHolder(
    private val binding: ItemSalesBinding,
    private val itemClick: (Roomsale) -> Unit
) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Roomsale){
        binding.run{
            category.text = item.category
            image.setImageResource(item.image)
            sale.text = " -${item.sale}% "
            root.setOnClickListener{
                itemClick(item)
            }
        }
    }
}