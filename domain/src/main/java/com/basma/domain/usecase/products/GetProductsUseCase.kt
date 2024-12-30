package com.basma.domain.usecase.products

import com.basma.domain.contract.products.ProductRepositoryContract

class GetProductsUseCase(private val productRepositoryContract: ProductRepositoryContract) {
    suspend operator fun invoke() =
        productRepositoryContract.getProductsList()
}