package com.basma.data.local.categories

class CategoryLocalDataSourceImpl(private val categoryDao: CategoryDao) :
    CategoryLocalDataSourceContract {
    override suspend fun getAllCategories(): List<CategoryLocalEntity> {
        return categoryDao.getAllCategories()
    }

    override suspend fun insertCategories(categories: List<CategoryLocalEntity>): List<Long> {
        return categoryDao.insertCategories(categories)
    }

    override suspend fun deleteAllCategories(): Int {
        return categoryDao.deleteAllCategories()
    }
}