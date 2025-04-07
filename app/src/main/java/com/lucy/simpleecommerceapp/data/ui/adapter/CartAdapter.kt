package com.lucy.simpleecommerceapp.data.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucy.simpleecommerceapp.data.db.CartItem
import com.lucy.simpleecommerceapp.databinding.ItemCartBinding

class CartAdapter(
    private val cartItems: List<CartItem>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>(){
    inner class CartViewHolder(val binding: ItemCartBinding) :
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        with(holder.binding) {
            tvCartName.text = item.name
            tvCartPrice.text = "$ ${item.price}"
            tvQuantity.text = "Qty: ${item.quantity}"
            Glide.with(imgCartItem).load(item.image).into((imgCartItem))
        }
    }

    override fun getItemCount(): Int =cartItems.size

}