package com.serelik.surfdailyquiz.quizstartscreen

import com.serelik.surfdailyquiz.domain.models.QuestionItem
import com.serelik.surfdailyquiz.quizstartscreen.models.QuestionUiModel

sealed interface QuizState {
    data object NotStarted : QuizState
    data object Loading : QuizState
    data object Error : QuizState
    data class Quiz(val question: QuestionUiModel) : QuizState

    data class QuizFinishUiModel(
        val correctCount: Int,
        val allCount: Int
    ) : QuizState
}
