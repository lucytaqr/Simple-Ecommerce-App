package com.lucy.simpleecommerceapp.data.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucy.simpleecommerceapp.databinding.ItemProductBinding
import com.lucy.simpleecommerceapp.model.Product

class ProductAdapter(
    private var products: List<Product>,
    private val onAddToCartClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder (val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        with(holder.binding) {
            tvProductName.text = product.title
            tvProductPrice.text = "$ ${product.price}"
            Glide.with(imgProduct).load(product.image).into(imgProduct)

            btnAddToCart.setOnClickListener{
                onAddToCartClick(product)
                Toast.makeText(root.context, "Added to cart", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = products.size

    fun updateProducts(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }
}