package com.example.techburg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.techburg.databinding.ItemProductBinding
import com.example.techburg.databinding.ItemSalesBinding

class ProductSalesAdapter(
    private var items: MutableList<Roomproduct>,
    private val itemClick: (Roomproduct) -> Unit
) : RecyclerView.Adapter<ProductSalesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSalesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductSalesViewHolder(
            binding = ItemProductBinding.inflate(layoutInflater,parent,false), itemClick
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductSalesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun filterList(filteredList: ArrayList<Roomproduct>){
        items = filteredList
        notifyDataSetChanged()
    }
}

class ProductSalesViewHolder(
    private val binding: ItemProductBinding,
    private val itemClick: (Roomproduct) -> Unit
) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Roomproduct){
        binding.run{
            name.text = item.name
            if (name.length() > 16){
                name.textSize = 14F
            }
            image.setImageResource(item.image.split(" ")[0].toInt())
            price.text = "USD ${item.price}"
            sale.text = " -${item.sale}% "
            root.setOnClickListener{
                itemClick(item)
            }
        }
    }
}