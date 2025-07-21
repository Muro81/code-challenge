package com.lukamurisic.lumiform_code_challenge.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.lukamurisic.lumiform_code_challenge.core.navigation.utils.Navigator
import com.lukamurisic.lumiform_code_challenge.core.navigation.utils.NavigatorImpl
import javax.inject.Singleton

//Binds navigation
@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractionModule {
    @Binds
    @Singleton
    abstract fun provideNavigator(
        navigatorImpl: NavigatorImpl
    ): Navigator
}