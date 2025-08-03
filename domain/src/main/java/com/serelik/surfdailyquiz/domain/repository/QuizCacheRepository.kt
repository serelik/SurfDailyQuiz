package com.serelik.surfdailyquiz.domain.repository

import com.serelik.surfdailyquiz.domain.models.QuestionItem

interface QuizCacheRepository {

    suspend fun addQuiz(quiz: QuestionItem) : Long

    suspend fun getQuiz(id: Long):QuestionItem

}