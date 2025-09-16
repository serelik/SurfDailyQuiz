package com.serelik.surfdailyquiz.quizstartscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serelik.surfdailyquiz.common.DrawStarCorrect
import com.serelik.surfdailyquiz.common.DrawStarIncorrect
import com.serelik.surfdailyquiz.common.MainButton
import com.serelik.surfdailyquiz.common.SummaryMessage
import com.serelik.core_n.R as CoreR

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
            stringResource(CoreR.string.summary),
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


                repeat(finishUiModel.resultSummaryUiModel.correctCount) {
                    DrawStarCorrect()
                }

                repeat(finishUiModel.resultSummaryUiModel.allCount - finishUiModel.resultSummaryUiModel.correctCount) {
                    DrawStarIncorrect()
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                stringResource(
                    CoreR.string.correct_from_all,
                    finishUiModel.resultSummaryUiModel.correctCount,
                    finishUiModel.resultSummaryUiModel.allCount
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            SummaryMessage(finishUiModel.resultSummaryUiModel)

            MainButton(
                onClick = { onNewQuizClick.invoke() },
                text = stringResource(CoreR.string.start_again),
            )

        }
    }
}