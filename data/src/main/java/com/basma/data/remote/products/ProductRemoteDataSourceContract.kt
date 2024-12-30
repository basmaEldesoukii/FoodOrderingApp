package com.basma.data.remote.products

import com.basma.data.remote.products.models.ProductsResponse

interface ProductRemoteDataSourceContract {
    suspend fun getProducts(): ProductsResponse
}