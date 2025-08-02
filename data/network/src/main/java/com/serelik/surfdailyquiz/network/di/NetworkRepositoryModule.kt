package com.serelik.surfdailyquiz.network.di

import com.serelik.surfdailyquiz.domain.repository.QuizRepository
import com.serelik.surfdailyquiz.network.repository.QuizRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkRepositoryModule {
    @Binds
    abstract fun bindBookRepository(repository: QuizRepositoryImpl): QuizRepository
}