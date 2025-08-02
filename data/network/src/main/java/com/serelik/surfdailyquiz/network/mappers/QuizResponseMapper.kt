package com.serelik.surfdailyquiz.network.mappers

import com.serelik.surfdailyquiz.domain.models.QuestionItem
import com.serelik.surfdailyquiz.network.models.QuizzesResponse
import javax.inject.Inject

class QuizResponseMapper @Inject() constructor() {
    fun convert(model: QuizzesResponse): List<QuestionItem> {
        val questionList = mutableListOf<QuestionItem>()

        model.results.forEach {
            questionList.add(QuestionItem(
                question = it.question,
                correctAnswer = it.correctAnswer,
                incorrectAnswers = it.incorrectAnswers,
            ))
        }

        return questionList
    }
}