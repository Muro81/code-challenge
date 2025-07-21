package com.lukamurisic.lumiform_code_challenge.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.lukamurisic.lumiform_code_challenge.data.repository.DataStoreImpl
import com.lukamurisic.lumiform_code_challenge.domain.repository.DataStoreRepository
import javax.inject.Singleton

//Binds repositories
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(
        dataStoreImpl: DataStoreImpl
    ): DataStoreRepository
}
