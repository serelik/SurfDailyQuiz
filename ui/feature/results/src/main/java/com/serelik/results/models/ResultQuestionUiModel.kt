package com.serelik.results.models

import com.serelik.surfdailyquiz.domain.models.QuestionItem

data class ResultQuestionUiModel(
    val questionCount: Int,
    val questionItem: QuestionItem,
    val selectedAnswer: String,
    val isAnswerRight: Boolean = questionItem.correctAnswer == selectedAnswer
)