package com.lukamurisic.lumiform_code_challenge.di

import android.app.Application
import com.lukamurisic.lumiform_code_challenge.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.lukamurisic.lumiform_code_challenge.core.utils.StringProvider
import com.lukamurisic.lumiform_code_challenge.core.utils.StringProviderImpl
import com.lukamurisic.lumiform_code_challenge.data.remote.services.ApiService
import com.squareup.moshi.Moshi
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//Provides okHttpClient, api service...(networking)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideResourceProvider(application: Application): StringProvider {
        return StringProviderImpl(application)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        val okHttpClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY }
            )
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideService(
        okHttpClient: OkHttpClient
    ): ApiService {
        val moshi = Moshi.Builder().build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}