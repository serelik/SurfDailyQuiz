package com.serelik.results

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val RESULTS_SCREEN_ROUTE = "results"

const val ID_KEY = "id"

fun NavGraphBuilder.results(
    onStartAgainClick: () -> Unit
) {
    composable(
        route = "$RESULTS_SCREEN_ROUTE/{$ID_KEY}",
        arguments = listOf(
            navArgument(ID_KEY) {
                type = NavType.LongType
                nullable = false
            }
        )

    ) {
        ResultsScreen(onStartAgainClick)
    }
}

fun NavController.navigateToResults(historyItemId: String) {
    navigate("$RESULTS_SCREEN_ROUTE/$historyItemId")
}
