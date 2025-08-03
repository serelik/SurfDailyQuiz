package com.serelik.surfdailyquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.serelik.surfdailyquiz.quizstartscreen.StartScreen
import com.serelik.core.theme.SurfDailyQuizTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SurfDailyQuizTheme {
                val navController = rememberNavController()
                QuizAppNavigation(navController)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SurfDailyQuizTheme {
        StartScreen( {})
    }
}