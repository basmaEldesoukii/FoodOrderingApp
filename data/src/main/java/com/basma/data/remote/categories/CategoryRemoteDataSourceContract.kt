package com.basma.data.remote.categories

import com.basma.data.remote.categories.models.CategoriesResponse

interface CategoryRemoteDataSourceContract {
    suspend fun getCategories(): CategoriesResponse
}