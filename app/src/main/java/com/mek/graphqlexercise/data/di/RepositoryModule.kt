package com.mek.graphqlexercise.data.di

import com.mek.graphqlexercise.data.repository.LaunchesRepositoryImpl
import com.mek.graphqlexercise.domain.repository.LaunchesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLaunchRepository(
        impl: LaunchesRepositoryImpl
    ): LaunchesRepository
}