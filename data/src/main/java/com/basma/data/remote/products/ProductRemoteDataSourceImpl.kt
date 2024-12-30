package com.basma.data.remote.products

import com.basma.data.remote.products.models.ProductResponse
import com.basma.data.remote.products.models.ProductsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProductRemoteDataSourceImpl(private val client: HttpClient) :
    ProductRemoteDataSourceContract {
    override suspend fun getProducts(): ProductsResponse {
        val products: List<ProductResponse> =
            client.get("https://my.api.mockaroo.com/products.json?key=0f59d800").body()
        return ProductsResponse(productsList = products)
    }
}
