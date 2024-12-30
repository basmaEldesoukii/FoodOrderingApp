package com.basma.foodorderingapp.di_modules

import com.basma.data.remote.categories.CategoryRemoteDataSourceContract
import com.basma.data.remote.categories.CategoryRemoteDataSourceImpl
import com.basma.data.remote.products.ProductRemoteDataSourceContract
import com.basma.data.remote.products.ProductRemoteDataSourceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.qualifier.named
import org.koin.dsl.module


val networkModule = module {

    single(named("client")) {
        HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            engine {
                requestTimeout = 0
            }
            install(ContentNegotiation) {
                json()
            }
        }
    }

    single<CategoryRemoteDataSourceContract>(named("category_remote_data_source")) {
        CategoryRemoteDataSourceImpl(get(named("client")))
    }

    single<ProductRemoteDataSourceContract>(named("product_remote_data_source")) {
        ProductRemoteDataSourceImpl(get(named("client")))
    }

}