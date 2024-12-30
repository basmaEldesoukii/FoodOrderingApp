package com.basma.data.repo.categories

import com.basma.common.utils.Resource
import com.basma.data.local.categories.CategoryLocalDataSourceContract
import com.basma.data.mapper.toCategoriesDataModel
import com.basma.data.mapper.toCategoryLocalEntityList
import com.basma.data.remote.categories.CategoryRemoteDataSourceContract
import com.basma.domain.contract.categories.CategoryRepositoryContract
import com.basma.domain.entity.categories.CategoriesDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepositoryImpl(
    private val categoryRemoteDataSourceContract: CategoryRemoteDataSourceContract,
    private val categoryLocalDataSourceContract: CategoryLocalDataSourceContract
) : CategoryRepositoryContract {
    override suspend fun getCategoriesList(): Flow<Resource<CategoriesDataModel>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val response = categoryRemoteDataSourceContract.getCategories()
                emit(Resource.Success(response.toCategoriesDataModel()))
                // Save to local
                categoryLocalDataSourceContract.insertCategories(response.toCategoryLocalEntityList())
            } catch (e: Exception) {
                // If remote request fails, get data from LocalDataSource
                try {
                    val cachedCategories = categoryLocalDataSourceContract.getAllCategories()
                    emit(Resource.Success(cachedCategories.toCategoriesDataModel()))

                } catch (e: Exception) {
                    emit(Resource.Error(e.message.toString()))
                }
            }
        }
    }
}

