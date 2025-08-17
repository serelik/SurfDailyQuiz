package com.serelik.surfdailyquiz.quizstartscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val QUIZ_SCREEN_ROUTE = "quiz"

fun NavGraphBuilder.quiz(
    onHistoryClick: () -> Unit
) {
    composable(QUIZ_SCREEN_ROUTE) {
        StartScreen(
            onHistoryClick = onHistoryClick
        )
    }


}

fun NavController.navigateToQuizStart() {
    navigate(QUIZ_SCREEN_ROUTE)
}