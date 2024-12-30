package com.basma.data.local.categories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categoriesListTable")
    suspend fun getAllCategories(): List<CategoryLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryLocalEntity>): List<Long>

    @Query("DELETE FROM categoriesListTable")
    suspend fun deleteAllCategories(): Int
}