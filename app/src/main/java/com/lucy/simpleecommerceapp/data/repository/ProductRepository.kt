package com.lucy.simpleecommerceapp.data.repository

import com.lucy.simpleecommerceapp.data.api.ProductApiService
import com.lucy.simpleecommerceapp.data.db.CartDao
import com.lucy.simpleecommerceapp.data.db.CartItem
import com.lucy.simpleecommerceapp.data.db.ProductDao
import com.lucy.simpleecommerceapp.data.db.ProductEntity
import com.lucy.simpleecommerceapp.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApiService,
    private val cartDao: CartDao,
    private val productDao: ProductDao
) {

    // API
    suspend fun getAllProducts(): List<Product> {
        return try {
            val products = api.getAllProducts()
            val entities = products.map {
                ProductEntity(
                    id = it.id,
                    title = it.title,
                    price = it.price,
                    description = it.description,
                    category = it.category,
                    image = it.image
                )
            }
            productDao.clearProducts()
            productDao.insertProducts(entities)
            products
        } catch (e: Exception) {
            val localProducts = productDao.getAllProducts()
            return localProducts.map {
                Product(
                    id = it.id,
                    title = it.title,
                    price = it.price,
                    description = it.description,
                    category = it.category,
                    image = it.image
                )
            }
        }
    }

    // Cart DB
    fun getCartItems(): Flow<List<CartItem>> = cartDao.getAllCartItems()

    suspend fun addToCart(item: CartItem) = cartDao.insertCartItem(item)

    suspend fun updateCartItem(item: CartItem) = cartDao.updateCartItem(item)

    suspend fun deleteCartItem(item: CartItem) = cartDao.deleteCartItem(item)

    suspend fun clearCart() = cartDao.clearCart()
}