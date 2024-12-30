package com.basma.data.local.categories

interface CategoryLocalDataSourceContract {
    suspend fun getAllCategories(): List<CategoryLocalEntity>
    suspend fun insertCategories(categories: List<CategoryLocalEntity>): List<Long>
    suspend fun deleteAllCategories(): Int
}