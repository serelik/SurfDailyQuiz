package com.serelik.surfdailyquiz.domain.models

data class QuestionItem(
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)
