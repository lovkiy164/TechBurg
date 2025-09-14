package com.example.techburg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.techburg.databinding.FragmentPage2Binding
import com.example.techburg.databinding.ItemHistoryBinding
import java.nio.file.Files.delete

class HistoryAdapter(
    private var items: MutableList<String>,
    private val itemClick: (Int,String) -> Unit
) : RecyclerView.Adapter<HistoryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistoryViewHolder(
            binding = ItemHistoryBinding.inflate(layoutInflater,parent,false), itemClick
        )
    }

    fun addElement(str: String){
        items.add(str)
        notifyItemInserted(items.size-1)
        notifyItemRangeChanged(items.size-1,items.size)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(items[position],position)
    }

    fun delete(position: Int){
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,items.size)
    }

    fun deleteAll(){
        items.removeAll(items)
        notifyDataSetChanged()
    }
}

class HistoryViewHolder(
    private val binding: ItemHistoryBinding,
    private val itemClick: (Int,String) -> Unit
) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: String, position: Int){
        binding.runCatching {
            name.text = item
            clear.setOnClickListener {
                itemClick(position,item)
            }
        }
    }
}