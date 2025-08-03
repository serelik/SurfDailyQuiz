package com.serelik.surfdailyquiz.data.database.di

import android.content.Context
import androidx.room.Room
import com.serelik.surfdailyquiz.data.database.QuizDatabase
import com.serelik.surfdailyquiz.data.database.dao.QuizDao
import com.serelik.surfdailyquiz.data.database.mapper.QuizEntityMapper
import com.serelik.surfdailyquiz.data.database.models.DbContract
import com.serelik.surfdailyquiz.data.database.repository.QuizCacheRepositoryImpl
import com.serelik.surfdailyquiz.domain.repository.QuizCacheRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {
    companion object {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext applicationContext: Context): QuizDatabase =
            Room.databaseBuilder(
                applicationContext,
                QuizDatabase::class.java,
                DbContract.DATABASE_NAME
            )
                .fallbackToDestructiveMigration(true)
                .build()

        @Provides
        fun provideBooksDao(database: QuizDatabase): QuizDao {
            return database.quizDao
        }
    }


    @Binds
    abstract fun bindQuizCacheRepository(repository: QuizCacheRepositoryImpl): QuizCacheRepository

}