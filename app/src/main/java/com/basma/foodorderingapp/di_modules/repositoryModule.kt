package com.basma.foodorderingapp.di_modules

import com.basma.data.repo.categories.CategoryRepositoryImpl
import com.basma.data.repo.products.ProductRepositoryImpl
import com.basma.domain.contract.categories.CategoryRepositoryContract
import com.basma.domain.contract.products.ProductRepositoryContract
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<CategoryRepositoryContract>(named("category_repository")) {
        CategoryRepositoryImpl(
            get(named("category_remote_data_source")),
            get(named("category_local_data_source"))
        )
    }
    single<ProductRepositoryContract>(named("product_repository")) {
        ProductRepositoryImpl(
            get(named("product_remote_data_source")),
            get(named("product_local_data_source"))
        )
    }
}

