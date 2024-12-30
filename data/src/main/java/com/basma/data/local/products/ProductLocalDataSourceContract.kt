package com.basma.data.local.products

interface ProductLocalDataSourceContract {
    suspend fun getAllProducts(): List<ProductLocalEntity>
    suspend fun insertProducts(products: List<ProductLocalEntity>): List<Long>
    suspend fun deleteAllProducts(): Int
}