package com.basma.foodorderingapp.di_modules

import com.basma.domain.usecase.categories.GetCategoriesUseCase
import com.basma.domain.usecase.products.GetProductsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val usecaseModule = module {
    factory(named("categories_usecase")) {
        GetCategoriesUseCase(get(named("category_repository")))
    }
    factory(named("products_usecase")) {
        GetProductsUseCase(get(named("product_repository")))
    }
}