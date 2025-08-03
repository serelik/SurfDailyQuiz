package com.serelik.surfdailyquiz.domain.models

data class QuestionItem(
    val id: Int,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val type: String,
    val category: String,
    val difficulty: String,
)
