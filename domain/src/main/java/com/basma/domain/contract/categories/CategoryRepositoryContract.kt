package com.basma.domain.contract.categories

import com.basma.common.utils.Resource
import com.basma.domain.entity.categories.CategoriesDataModel
import kotlinx.coroutines.flow.Flow

interface CategoryRepositoryContract {
    suspend fun getCategoriesList(): Flow<Resource<CategoriesDataModel>>
}