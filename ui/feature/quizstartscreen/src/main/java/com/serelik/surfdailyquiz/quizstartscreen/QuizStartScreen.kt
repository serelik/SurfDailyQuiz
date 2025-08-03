package com.serelik.surfdailyquiz.quizstartscreen


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.serelik.quizstartscreen.R

@Composable
fun StartScreen(
    onHistoryClick: () -> Unit,
) {

    val viewModel: QuizStartViewModel = hiltViewModel()

    val context = LocalContext.current

    val quizState = viewModel.quizStateFlow.collectAsState()

    val insets = WindowInsets.systemBars.asPaddingValues()

    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxHeight()
            .padding(insets)
            .verticalScroll(rememberScrollState()),

        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        when (quizState.value) {
            QuizState.NotStarted -> StartScreenUi(
                viewModel::onStartQuizClick,
                onHistoryClick = onHistoryClick
            )

            QuizState.Error -> {
                StartScreenUi(
                    viewModel::onStartQuizClick,
                    onHistoryClick = onHistoryClick
                )
                Toast.makeText(context, "Ошибка! Попробуйте ещё раз", Toast.LENGTH_SHORT).show()
            }

            is QuizState.Quiz -> {
                QuizScreen(
                    (quizState.value as QuizState.Quiz).question,
                    onNextClick = viewModel::nextClick,
                    onSelectClick = viewModel::selectAnswer
                )
            }

            QuizState.Loading -> LoaderView()
            is QuizState.QuizFinishUiModel -> QuizFinishedScreen(
                finishUiModel = quizState.value as QuizState.QuizFinishUiModel,
                viewModel::onNewQuizClick
            )
        }

    }

}

@Composable
fun StartScreenUi(
    onStartClick: () -> Unit,
    onHistoryClick: () -> Unit
) {
    Button(
        onClick = {
            onHistoryClick.invoke()
        },
        modifier = Modifier.padding(top = 52.dp),
        enabled = true,
        contentPadding = PaddingValues(12.dp),
        shape = ShapeDefaults.ExtraLarge,

        ) {

        Text(
            text = stringResource(R.string.history_button_name),
            color = MaterialTheme.colorScheme.background
        )

        Spacer(Modifier.width(12.dp))

        Icon(
            painter = painterResource(R.drawable.history_icon),
            contentDescription = "history Icon",
            tint = MaterialTheme.colorScheme.background,
            modifier = Modifier
        )
    }

    Icon(
        painter = painterResource(R.drawable.title_icon),
        contentDescription = "history Icon",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
            .padding(top = 120.dp),
        tint = MaterialTheme.colorScheme.primary
    )

    Column(
        modifier = Modifier
            .padding(top = 32.dp)
            .background(
                color = Color.White,
                shape = ShapeDefaults.ExtraLarge
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.quiz_greetings_title),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp, bottom = 40.dp),
            style = MaterialTheme.typography.titleLarge
        )

        Button(
            onClick = {
                onStartClick.invoke()
            },
            shape = ShapeDefaults.Medium,
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                disabledContentColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .padding(bottom = 24.dp)
                .requiredSize(width = 260.dp, height = 50.dp)

        ) {
            Text(text = stringResource(R.string.quiz_start))
        }

    }
}

