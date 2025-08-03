package com.serelik.surfdailyquiz.domain.repository

import com.serelik.surfdailyquiz.domain.models.QuestionItem

interface QuizRepository {
    suspend fun getQuiz(): List<QuestionItem>
}