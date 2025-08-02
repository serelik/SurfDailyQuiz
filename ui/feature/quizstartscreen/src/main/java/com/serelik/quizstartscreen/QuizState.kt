package com.serelik.quizstartscreen

import com.serelik.surfdailyquiz.domain.models.QuestionItem

sealed interface QuizState {
    data object NotStarted : QuizState
    data object Loading : QuizState
    data object Error : QuizState
    data class Result(val questionList: List<QuestionItem>) : QuizState
}
