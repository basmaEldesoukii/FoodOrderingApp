package com.basma.data.repo.products

import com.basma.common.utils.Resource
import com.basma.data.local.products.ProductLocalDataSourceContract
import com.basma.data.mapper.toProductLocalEntityList
import com.basma.data.mapper.toProductsDataModel
import com.basma.data.remote.products.ProductRemoteDataSourceContract
import com.basma.domain.contract.products.ProductRepositoryContract
import com.basma.domain.entity.products.ProductsDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl(
    private val productRemoteDataSourceContract: ProductRemoteDataSourceContract,
    private val productLocalDataSourceContract: ProductLocalDataSourceContract
) : ProductRepositoryContract {
    override suspend fun getProductsList(): Flow<Resource<ProductsDataModel>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val response = productRemoteDataSourceContract.getProducts()
                emit(Resource.Success(response.toProductsDataModel()))
                // Save to local
                productLocalDataSourceContract.insertProducts(response.toProductLocalEntityList())
            } catch (e: Exception) {
                // If remote request fails, get data from LocalDataSource
                try {
                    val cachedProducts = productLocalDataSourceContract.getAllProducts()
                    emit(Resource.Success(cachedProducts.toProductsDataModel()))

                } catch (e: Exception) {
                    emit(Resource.Error(e.message.toString()))
                }
            }
        }
    }
}