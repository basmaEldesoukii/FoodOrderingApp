package com.basma.data.local.products

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM productsListTable")
    suspend fun getAllProducts(): List<ProductLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductLocalEntity>): List<Long>

    @Query("DELETE FROM productsListTable")
    suspend fun deleteAllProducts(): Int
}