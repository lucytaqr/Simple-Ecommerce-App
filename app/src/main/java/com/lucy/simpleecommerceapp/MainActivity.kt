package com.lucy.simpleecommerceapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.lucy.simpleecommerceapp.data.db.CartDao
import com.lucy.simpleecommerceapp.data.db.CartItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var cartDao: CartDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            cartDao.insertCartItem(
                CartItem(productId = 1, name = "Test", price = 20.0, quantity = 2, image = "")
            )
            cartDao.getAllCartItems().collect {
                Log.d("CART", it.toString())
            }
        }
    }
}