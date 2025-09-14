package com.example.techburg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.techburg.databinding.ItemHistoryBinding
import com.example.techburg.databinding.ItemSearchBinding

class LastViewedHistoryAdapter(
    private var items: MutableList<Roomproduct>,
    private val itemClick: (Roomproduct) -> Unit
) : RecyclerView.Adapter<LastHistoryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastHistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LastHistoryViewHolder(
            binding = ItemSearchBinding.inflate(layoutInflater,parent,false), itemClick
        )
    }

    fun addElement(product: Roomproduct){
        items.add(product)
        notifyItemInserted(items.size-1)
        notifyItemRangeChanged(items.size-1,items.size)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LastHistoryViewHolder, position: Int) {
        holder.bind(items[position],position)
    }

    fun delete(position: Int){
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,items.size)
    }
}

class LastHistoryViewHolder(
    private val binding: ItemSearchBinding,
    private val itemClick: (Roomproduct) -> Unit
) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Roomproduct, position: Int){
        binding.runCatching {
            name.text = item.name
            price.text = "USD ${item.price}"
            image.setImageResource(item.image.split(" ").map {
                it.toInt()
            }[0])
            root.setOnClickListener {
                itemClick(item)
            }
        }
    }
}