package com.lucy.simpleecommerceapp.data.api

import com.lucy.simpleecommerceapp.model.Product
import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getAllProducts(): List<Product>
}