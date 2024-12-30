package com.basma.data.local.categories

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CategoryLocalEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CategoryDatabase : RoomDatabase() {
    abstract fun categoriesDao(): CategoryDao
}