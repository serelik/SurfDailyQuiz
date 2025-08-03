package com.serelik.surfdailyquiz.domain.repository

import com.serelik.surfdailyquiz.domain.models.QuestionItem

interface QuizCacheRepository {

    suspend fun addQuiz(quiz: QuestionItem) : Int

    suspend fun getQuiz(id: Int):QuestionItem

}