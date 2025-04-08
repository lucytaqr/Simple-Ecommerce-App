package com.lucy.simpleecommerceapp.data.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucy.simpleecommerceapp.data.db.CartItem
import com.lucy.simpleecommerceapp.databinding.ItemCartBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

    private var items: List<CartItem> = emptyList()

    fun submitList(list: List<CartItem>) {
        items = list
        notifyDataSetChanged()
    }

    inner class CartViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvCartName.text = item.name
            tvCartPrice.text = "$ ${item.price} x ${item.quantity}"
            Glide.with(imgCartItem).load(item.image).into((imgCartItem))
        }
    }

    override fun getItemCount(): Int =items.size

}