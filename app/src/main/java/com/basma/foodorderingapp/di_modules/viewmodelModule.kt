package com.basma.foodorderingapp.di_modules

import com.basma.presentation.viewmodel.TablesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        TablesViewModel(
            get(named("categories_usecase")),
            get(named("products_usecase"))
        )
    }
}