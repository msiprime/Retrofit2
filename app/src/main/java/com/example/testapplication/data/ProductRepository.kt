package com.example.testapplication.data

import com.example.testapplication.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductsList():Flow<Result<List<Product>>>
}