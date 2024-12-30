package com.basma.data.local.products

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ProductLocalEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductDao

}