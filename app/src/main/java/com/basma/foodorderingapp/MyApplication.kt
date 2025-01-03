package com.basma.foodorderingapp

import android.app.Application
import com.basma.foodorderingapp.di_modules.databaseModule
import com.basma.foodorderingapp.di_modules.networkModule
import com.basma.foodorderingapp.di_modules.repositoryModule
import com.basma.foodorderingapp.di_modules.usecaseModule
import com.basma.foodorderingapp.di_modules.viewModelsModule
import io.ktor.client.HttpClient
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.qualifier.named

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                networkModule,
                databaseModule,
                repositoryModule,
                usecaseModule,
                viewModelsModule,
            )
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        getKoin().get<HttpClient>(named("client")).close()
        getKoin().close()
    }
}