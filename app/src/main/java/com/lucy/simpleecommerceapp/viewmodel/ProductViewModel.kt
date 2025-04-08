package com.lucy.simpleecommerceapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucy.simpleecommerceapp.data.db.CartItem
import com.lucy.simpleecommerceapp.data.repository.ProductRepository
import com.lucy.simpleecommerceapp.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    val productList: StateFlow<List<Product>> = _productList.asStateFlow()

    val cartItems = repository.getCartItems()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun fetchProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val products = repository.getAllProducts()
                _productList.value = products
            } catch (e: Exception) {
                // TODO: handle error
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            val cartItem = CartItem(
                productId = product.id,
                name = product.title,
                price = product.price,
                quantity = 1,
                image = product.image
            )
            repository.addToCart(cartItem)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }
}