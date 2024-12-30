package com.basma.data.remote.categories

import com.basma.data.remote.categories.models.CategoriesResponse
import com.basma.data.remote.categories.models.CategoryResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CategoryRemoteDataSourceImpl(private val client: HttpClient) :
    CategoryRemoteDataSourceContract {
    override suspend fun getCategories(): CategoriesResponse {
        val categories: List<CategoryResponse> =
            client.get("https://my.api.mockaroo.com/categories.json?key=0f59d800").body()
        return CategoriesResponse(categoriesList = categories)
    }
}