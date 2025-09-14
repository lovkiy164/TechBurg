package com.example.techburg

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.techburg.databinding.FragmentRecommended1Binding

class PageAdapter(
    private var items: MutableList<Roomproductrec>,
    private val itemClick: (Roomproductrec) -> Unit
) : RecyclerView.Adapter<PageViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PageViewHolder(
            binding = FragmentRecommended1Binding.inflate(layoutInflater,parent,false), itemClick
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class PageViewHolder(
    private val binding: FragmentRecommended1Binding,
    private val itemClick: (Roomproductrec) -> Unit
) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Roomproductrec){
        binding.runCatching {
            image.setImageResource(item.image)
            name.text = item.name
            price.text = "USD ${item.price}"
            btn.setOnClickListener {
                Log.d("Log","true")
                itemClick(item)
            }
        }
    }
}