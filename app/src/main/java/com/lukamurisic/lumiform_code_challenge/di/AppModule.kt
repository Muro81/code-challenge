package com.lukamurisic.lumiform_code_challenge.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.lukamurisic.lumiform_code_challenge.core.utils.StringProvider
import com.lukamurisic.lumiform_code_challenge.core.utils.StringProviderImpl
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

}