package com.serelik.surfdailyquiz.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serelik.surfdailyquiz.data.database.models.DbContract
import com.serelik.surfdailyquiz.data.database.models.HistoryEntity
import com.serelik.surfdailyquiz.data.database.models.QuizEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun addQuiz(quiz: QuizEntity): Long

    @Query("SELECT * FROM ${DbContract.Quizzes.TABLE_NAME} WHERE _id IN (:ids)")
    suspend fun getQuestionsByIds(ids: List<Long>): List<QuizEntity>

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun addHistory(historyItem: HistoryEntity)

    @Query("SELECT * FROM ${DbContract.History.TABLE_NAME}")
    fun getQuizzesHistory(): Flow<List<HistoryEntity>>

}