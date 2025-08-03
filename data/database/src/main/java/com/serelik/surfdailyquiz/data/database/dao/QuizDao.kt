package com.serelik.surfdailyquiz.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serelik.surfdailyquiz.data.database.models.QuizEntity

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun addQuiz(quiz: QuizEntity): Long

    @Query("SELECT * FROM quizzes WHERE _id = :id")
    suspend fun getQuiz(id: Long) : QuizEntity
}