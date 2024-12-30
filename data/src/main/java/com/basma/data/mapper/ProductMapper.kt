package com.basma.data.mapper

import com.basma.data.local.products.ProductLocalEntity
import com.basma.data.remote.products.models.ProductResponse
import com.basma.data.remote.products.models.ProductsResponse
import com.basma.domain.entity.products.ProductDataModel
import com.basma.domain.entity.products.ProductsDataModel

fun ProductResponse.toProductDataModel() = ProductDataModel(
    id = this.id,
    categoryDataModel = this.category.toCategoryDataModel(),
    name = this.name,
    description = this.description,
    image = this.image,
    price = this.price,
)

fun ProductsResponse.toProductsDataModel() = ProductsDataModel(
    productsList = this.productsList.map { it.toProductDataModel() }
)

fun ProductResponse.toProductLocalEntity() = ProductLocalEntity(
    id = this.id,
    categoryResponse = this.category,
    name = this.name,
    description = this.description,
    image = this.image,
    price = this.price,
)

fun ProductLocalEntity.toProductDataModel() = ProductDataModel(
    id = this.id,
    categoryDataModel = this.categoryResponse.toCategoryDataModel(),
    name = this.name,
    description = this.description,
    image = this.image,
    price = this.price,
)

fun ProductsResponse.toProductLocalEntityList() = productsList.map { it.toProductLocalEntity() }


fun List<ProductLocalEntity>.toProductsDataModel() = ProductsDataModel(
    productsList = this.map { it.toProductDataModel() }
)