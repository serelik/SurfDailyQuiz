package com.serelik.surfdailyquiz.domain.models

data class QuestionItem(
    val id: Long,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val type: String,
    val category: String,
    val difficulty: String,
) {
    val answers: List<String> = (listOf<String>(correctAnswer) + incorrectAnswers).shuffled()

}
