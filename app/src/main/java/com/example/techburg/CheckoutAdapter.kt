package com.example.techburg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.techburg.databinding.ItemCheckoutBinding
import com.example.techburg.databinding.ItemSalesBinding

class CheckoutAdapter(
    private var items: MutableList<Roomcart>,
    private val itemClick: (Int,Roomcart) -> Unit
) : RecyclerView.Adapter<CheckoutViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CheckoutViewHolder(
            binding = ItemCheckoutBinding.inflate(layoutInflater,parent,false), itemClick
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun removeAt(position: Int){
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,items.size)
    }

    fun filterList(filteredList: ArrayList<Roomcart>){
        items = filteredList
        notifyDataSetChanged()
    }
}

class CheckoutViewHolder(
    private val binding: ItemCheckoutBinding,
    private val itemClick: (Int,Roomcart) -> Unit
) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Roomcart){
        binding.run{
            name.text = item.name
            image.setImageResource(item.image)
            price.text = "USD ${item.price}"
            if (item.sale < 0) {
                sale.text = " -${item.sale}% "
            }
            else{
                sale.isVisible = false
            }
            if (item.color != null){
                color.text = item.color
            }
            else{
                color.isVisible = false
            }
            delete.setOnClickListener{
                itemClick(position,item)
            }
        }
    }
}