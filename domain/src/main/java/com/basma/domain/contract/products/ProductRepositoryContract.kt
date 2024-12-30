package com.basma.domain.contract.products

import com.basma.common.utils.Resource
import com.basma.domain.entity.products.ProductsDataModel
import kotlinx.coroutines.flow.Flow

interface ProductRepositoryContract {
    suspend fun getProductsList(): Flow<Resource<ProductsDataModel>>
}