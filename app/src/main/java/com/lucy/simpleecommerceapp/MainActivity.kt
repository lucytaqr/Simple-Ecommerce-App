package com.lucy.simpleecommerceapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucy.simpleecommerceapp.data.ui.adapter.ProductAdapter
import com.lucy.simpleecommerceapp.databinding.ActivityMainBinding
import com.lucy.simpleecommerceapp.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productAdapter = ProductAdapter(listOf()) { product ->
            viewModel.addToCart(product)
        }

        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = productAdapter
        }

        viewModel.fetchProducts()

        lifecycleScope.launchWhenStarted {
            viewModel.productList.collect { products ->
                productAdapter.updateProducts(products)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }

        binding.btnCheckout.setOnClickListener{
            startActivity(Intent(this, CheckoutActivity::class.java))
        }
    }
}