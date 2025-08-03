package com.serelik.surfdailyquiz.domain.repository

import com.serelik.surfdailyquiz.domain.models.HistoryListItem
import com.serelik.surfdailyquiz.domain.models.QuestionItem
import kotlinx.coroutines.flow.Flow

interface QuizCacheRepository {

    suspend fun addQuiz(quiz: QuestionItem): Long

    suspend fun getQuiz(id: List<Long>): List<QuestionItem>

    suspend fun saveHistory(
        questionItem: List<QuestionItem>,
        answers: Map<String, String>,
        correctAnswersCount: Int
    )

    fun getHistoryItem(): Flow<List<HistoryListItem>>

}