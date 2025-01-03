package com.basma.foodorderingapp.di_modules

import android.content.Context
import androidx.room.Room
import com.basma.data.local.categories.CategoryDatabase
import com.basma.data.local.categories.CategoryLocalDataSourceContract
import com.basma.data.local.categories.CategoryLocalDataSourceImpl
import com.basma.data.local.products.ProductDatabase
import com.basma.data.local.products.ProductLocalDataSourceContract
import com.basma.data.local.products.ProductLocalDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val databaseModule = module {

    single(named("categories_database")) {
        Room.databaseBuilder(get<Context>(), CategoryDatabase::class.java, "categories_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    single(named("categories_dao")) {
        get<CategoryDatabase>(named("categories_database")).categoriesDao()
    }

    single<CategoryLocalDataSourceContract>(named("category_local_data_source")) {
        CategoryLocalDataSourceImpl(get(named("categories_dao")))
    }

    single(named("products_database")) {
        Room.databaseBuilder(get<Context>(), ProductDatabase::class.java, "products_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    single(named("products_dao")) {
        get<ProductDatabase>(named("products_database")).productsDao()
    }

    single<ProductLocalDataSourceContract>(named("product_local_data_source")) {
        ProductLocalDataSourceImpl(get(named("products_dao")))
    }

}

