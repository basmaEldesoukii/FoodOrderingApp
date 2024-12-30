package com.basma.data.local.products

class ProductLocalDataSourceImpl(private val productDao: ProductDao) :
    ProductLocalDataSourceContract {
    override suspend fun getAllProducts(): List<ProductLocalEntity> {
        return productDao.getAllProducts()
    }

    override suspend fun insertProducts(products: List<ProductLocalEntity>): List<Long> {
        return productDao.insertProducts(products)
    }

    override suspend fun deleteAllProducts(): Int {
        return productDao.deleteAllProducts()
    }
}