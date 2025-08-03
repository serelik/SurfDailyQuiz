package com.serelik.surfdailyquiz.ui.feature.history

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HISTORY_SCREEN_ROUTE = "history"

fun NavGraphBuilder.history(
) {
    composable(HISTORY_SCREEN_ROUTE) {
        HistoryListScreen()
    }
}

fun NavController.navigateToHistory() {
    navigate(HISTORY_SCREEN_ROUTE)
}

