package com.basma.data.mapper

import com.basma.data.local.categories.CategoryLocalEntity
import com.basma.data.remote.categories.models.CategoriesResponse
import com.basma.data.remote.categories.models.CategoryResponse
import com.basma.domain.entity.categories.CategoriesDataModel
import com.basma.domain.entity.categories.CategoryDataModel


fun CategoryDataModel.toCategoryResponse() = CategoryResponse(
    id = this.id,
    name = this.name
)


fun CategoryResponse.toCategoryDataModel() = CategoryDataModel(
    id = this.id,
    name = this.name
)

fun CategoriesResponse.toCategoriesDataModel() = CategoriesDataModel(
    categoriesList = this.categoriesList.map { it.toCategoryDataModel() }
)

fun CategoryResponse.toCategoryLocalEntity() = CategoryLocalEntity(
    id = this.id,
    name = this.name
)

fun CategoryLocalEntity.toCategoryDataModel() = CategoryDataModel(
    id = this.id,
    name = this.name
)

fun CategoriesResponse.toCategoryLocalEntityList() =
    categoriesList.map { it.toCategoryLocalEntity() }


fun List<CategoryLocalEntity>.toCategoriesDataModel() = CategoriesDataModel(
    categoriesList = this.map { it.toCategoryDataModel() }
)


