package com.serelik.surfdailyquiz.quizstartscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serelik.quizstartscreen.R
import com.serelik.core_n.R as CoreR
import com.serelik.surfdailyquiz.ui.theme.SurfDailyQuizTheme

@Composable
fun QuizFinishedScreen(
    finishUiModel: QuizState.QuizFinishUiModel,
    onNewQuizClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp)
    ) {
        Text(
            stringResource(R.string.summary),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 36.dp)
        )

        Column(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(
                    color = Color.White,
                    shape = ShapeDefaults.ExtraLarge
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Row {


                repeat(finishUiModel.correctCount) {
                    DrawStarCorrect()
                }

                repeat(finishUiModel.allCount - finishUiModel.correctCount) {
                    DrawStarIncorrect()
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                stringResource(
                    R.string.correct_from_all, finishUiModel.correctCount, finishUiModel.allCount
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            SummaryMessage(finishUiModel)

            Button(
                onClick = { onNewQuizClick.invoke() },
                shape = ShapeDefaults.Medium,
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                    disabledContentColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .padding(top = 52.dp, bottom = 24.dp)
                    .requiredSize(width = 260.dp, height = 50.dp)

            ) {
                Text(
                    text = stringResource(R.string.start_again),
                )
            }

        }
    }
}

@Composable
fun DrawStarCorrect() {
    Icon(
        painterResource(CoreR.drawable.star_icon),
        contentDescription = null,
        modifier = Modifier
            .padding(horizontal = 4.dp)
        ,
        tint = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun DrawStarIncorrect() {
    Icon(
        painterResource(CoreR.drawable.star_icon),
        contentDescription = null,
        modifier = Modifier
            .padding(horizontal = 4.dp),
        tint = MaterialTheme.colorScheme.tertiary
    )
}

@Composable
fun SummaryMessage(finishUiModel: QuizState.QuizFinishUiModel) {
    val summaryTitle = getSummaryTitle(finishUiModel.correctCount)
    val summaryMessage = getSummaryMessage(finishUiModel.correctCount)
    Text(
        text = summaryTitle,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier,
        textAlign = TextAlign.Center
    )



    Text(
        text = summaryMessage,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .padding(horizontal = 12.dp),
        textAlign = TextAlign.Center
    )

}

@Composable
fun getSummaryTitle(count: Int): String {
    return when (count) {
        0 -> stringResource(R.string.summary_Title_0)
        1 -> stringResource(R.string.summary_Title_1)
        2 -> stringResource(R.string.summary_Title_2)
        3 -> stringResource(R.string.summary_Title_3)
        4 -> stringResource(R.string.summary_Title_4)
        5 -> stringResource(R.string.summary_Title_5)
        else -> "Что то пошло не так"
    }
}

@Composable
fun getSummaryMessage(count: Int): String {
    return when (count) {
        0 -> stringResource(R.string.summary_message_0)
        1 -> stringResource(R.string.summary_message_1)
        2 -> stringResource(R.string.summary_message_2)
        3 -> stringResource(R.string.summary_message_3)
        4 -> stringResource(R.string.summary_message_4)
        5 -> stringResource(R.string.summary_message_5)
        else -> "Что то пошло не так"
    }
}

@Preview
@Composable
fun PreviewCheck() {
    SurfDailyQuizTheme {
        QuizFinishedScreen(
            QuizState.QuizFinishUiModel(
                correctCount = 3,
                allCount = 5
            )
        )
    }
}