package com.serelik.surfdailyquiz.quizstartscreen

sealed interface QuizState {
    data object NotStarted : QuizState
    data object Loading : QuizState
    data object Error : QuizState
    data class Result(val questionIdList: List<Long>) : QuizState
}
