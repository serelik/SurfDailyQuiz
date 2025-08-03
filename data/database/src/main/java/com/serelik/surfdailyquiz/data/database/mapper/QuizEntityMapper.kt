package com.serelik.surfdailyquiz.data.database.mapper

import com.serelik.surfdailyquiz.data.database.models.QuizEntity
import com.serelik.surfdailyquiz.domain.models.QuestionItem
import javax.inject.Inject

 class QuizEntityMapper @Inject constructor() {

    fun toEntity(questionItem: QuestionItem): QuizEntity {

        return QuizEntity(
            id = questionItem.id,
            type = questionItem.type,
            difficulty = questionItem.difficulty,
            question = questionItem.question,
            correctAnswer = questionItem.correctAnswer,
            incorrectAnswers = questionItem.incorrectAnswers.joinToString(", "),
            category = questionItem.category
        )
    }

    fun fromEntity(quizEntity: QuizEntity): QuestionItem {

        return QuestionItem(
            id = quizEntity.id,
            question = quizEntity.question,
            correctAnswer = quizEntity.correctAnswer,
            incorrectAnswers = quizEntity.incorrectAnswers.split(", "),
            type = quizEntity.type,
            category = quizEntity.category,
            difficulty = quizEntity.difficulty
        )
    }
}