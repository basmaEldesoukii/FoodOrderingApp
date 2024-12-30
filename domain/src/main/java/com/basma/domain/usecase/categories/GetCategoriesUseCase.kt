package com.basma.domain.usecase.categories

import com.basma.domain.contract.categories.CategoryRepositoryContract

class GetCategoriesUseCase(private val categoriesRepositoryContract: CategoryRepositoryContract) {
    suspend operator fun invoke() =
        categoriesRepositoryContract.getCategoriesList()
}