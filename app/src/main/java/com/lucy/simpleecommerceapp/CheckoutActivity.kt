package com.lucy.simpleecommerceapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.lucy.simpleecommerceapp.data.ui.adapter.CartAdapter
import com.lucy.simpleecommerceapp.databinding.ActivityCheckoutBinding
import com.lucy.simpleecommerceapp.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()
    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            viewModel.cartItems.collect { cart ->
                binding.rvCart.adapter = CartAdapter(cart)
            }
        }

        binding.btnPay.setOnClickListener{
            Toast.makeText(this, "Checkout sukses \uD83C\uDF89", Toast.LENGTH_SHORT).show()
        }
    }
}