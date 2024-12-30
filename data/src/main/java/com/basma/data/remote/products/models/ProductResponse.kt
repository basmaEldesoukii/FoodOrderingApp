package com.basma.data.remote.products.models

import com.basma.data.remote.categories.models.CategoryResponse
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: String,
    val category: CategoryResponse,
    val name: String,
    val description: String?,
    val image: String,
    val price: Double,
)