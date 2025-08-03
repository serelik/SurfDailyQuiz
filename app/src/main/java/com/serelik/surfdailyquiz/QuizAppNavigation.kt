package com.serelik.surfdailyquiz

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.serelik.surfdailyquiz.quizstartscreen.QUIZ_SCREEN_ROUTE
import com.serelik.surfdailyquiz.quizstartscreen.quiz
import com.serelik.surfdailyquiz.ui.feature.history.history
import com.serelik.surfdailyquiz.ui.feature.history.navigateToHistory

@Composable
fun QuizAppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = QUIZ_SCREEN_ROUTE,
    ) {
        quiz(onHistoryClick = navController::navigateToHistory)

        history(onBackClick = navController::popBackStack)
    }
}
