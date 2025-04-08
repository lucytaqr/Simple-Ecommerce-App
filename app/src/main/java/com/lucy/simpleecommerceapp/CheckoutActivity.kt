package com.lucy.simpleecommerceapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucy.simpleecommerceapp.data.ui.adapter.CartAdapter
import com.lucy.simpleecommerceapp.databinding.ActivityCheckoutBinding
import com.lucy.simpleecommerceapp.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartAdapter = CartAdapter()
        binding.rvCart.apply {
            layoutManager = LinearLayoutManager(this@CheckoutActivity)
            adapter = cartAdapter
        }

        lifecycleScope.launch {
            viewModel.cartItems.collect { cartList ->
                cartAdapter.submitList(cartList)

                val total = cartList.sumOf { it.price * it.quantity }
                binding.tvTotal.text = "Total: $${"%.2f".format(total)}"

            }
        }

        binding.btnClearCart.setOnClickListener{
            lifecycleScope.launch {
                viewModel.clearCart()
            }
        }
    }
}