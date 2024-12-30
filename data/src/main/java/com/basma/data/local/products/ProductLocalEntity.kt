package com.basma.data.local.products

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.basma.data.remote.categories.models.CategoryResponse

@Entity(tableName = "productsListTable")
data class ProductLocalEntity(
    @PrimaryKey
    val id: String,
    val categoryResponse: CategoryResponse,
    val name: String,
    val description: String?,
    val image: String,
    val price: Double
)
