package com.basma.data.remote.categories.models

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponse(val categoriesList: List<CategoryResponse>)