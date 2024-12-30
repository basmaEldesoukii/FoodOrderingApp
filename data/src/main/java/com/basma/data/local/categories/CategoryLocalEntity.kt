package com.basma.data.local.categories

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoriesListTable")
data class CategoryLocalEntity(
    @PrimaryKey
    val id: String,
    val name: String,
)
