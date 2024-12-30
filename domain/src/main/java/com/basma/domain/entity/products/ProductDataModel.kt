package com.basma.domain.entity.products

import com.basma.domain.entity.categories.CategoryDataModel

data class ProductDataModel(
    val id: String,
    val categoryDataModel: CategoryDataModel,
    val name: String,
    val description: String?,
    val image: String,
    val price: Double,
)
