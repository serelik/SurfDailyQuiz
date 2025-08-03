package com.serelik.surfdailyquiz.data.database.mapper

import com.serelik.surfdailyquiz.data.database.models.QuizEntity
import com.serelik.surfdailyquiz.domain.models.QuestionItem
import javax.inject.Inject

 class QuizEntityMapper @Inject constructor() {

     companion object {
         private const val SEPARATOR = "$%#"
     }
    fun toEntity(questionItem: QuestionItem): QuizEntity {


        return QuizEntity(
            id = null,
            type = questionItem.type,
            difficulty = questionItem.difficulty,
            question = questionItem.question,
            correctAnswer = questionItem.correctAnswer,
            incorrectAnswers = questionItem.incorrectAnswers.joinToString(SEPARATOR),
            category = questionItem.category
        )
    }

    fun fromEntity(quizEntity: QuizEntity): QuestionItem {

        return QuestionItem(
            id = quizEntity.id?: -1,
            question = quizEntity.question,
            correctAnswer = quizEntity.correctAnswer,
            incorrectAnswers = quizEntity.incorrectAnswers.split(SEPARATOR),
            type = quizEntity.type,
            category = quizEntity.category,
            difficulty = quizEntity.difficulty
        )
    }
}