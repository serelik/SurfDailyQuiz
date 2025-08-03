package com.serelik.surfdailyquiz.quizstartscreen.models

import com.serelik.surfdailyquiz.domain.models.QuestionItem

data class QuestionUiModel(
    val questionNumber: Int,
    val questionCount: Int,
    val questionItem: QuestionItem,
    val selectAnswer: String?
)