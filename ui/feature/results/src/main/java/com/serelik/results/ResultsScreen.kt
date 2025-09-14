package com.serelik.results

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.serelik.core.theme.White
import com.serelik.results.models.ResultQuestionUiModel
import com.serelik.results.models.ResultSummaryUiModel
import com.serelik.surfdailyquiz.common.DrawStarCorrect
import com.serelik.surfdailyquiz.common.DrawStarIncorrect
import com.serelik.surfdailyquiz.common.LoaderView
import com.serelik.surfdailyquiz.common.MainButton
import com.serelik.surfdailyquiz.common.getSummaryMessage
import com.serelik.surfdailyquiz.common.getSummaryTitle
import com.serelik.core_n.R as CoreR

@Composable
fun ResultsScreen(
    onStartAgainClick: () -> Unit
) {

    val viewModel: ResultsViewModel = hiltViewModel()

    val quizStateFlow = viewModel.quizStateFlow.collectAsState()

    when (quizStateFlow.value) {
        ResultState.Loading -> LoaderView()
        is ResultState.Result -> ResultView(
            resultQuestionUiModel = (quizStateFlow.value as ResultState.Result).resultQuestionUiModel,
            resultSummaryUiModel = (quizStateFlow.value as ResultState.Result).resultSummaryUiModel,
            onStartAgainClick = onStartAgainClick,
        )
    }

}

@Composable
fun ResultView(
    resultQuestionUiModel: List<ResultQuestionUiModel>,
    resultSummaryUiModel: ResultSummaryUiModel,
    onStartAgainClick: () -> Unit
) {

    val insets = WindowInsets.systemBars.asPaddingValues()

    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxHeight()
            .padding(insets)
            .verticalScroll(rememberScrollState()),

        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Text(
            stringResource(CoreR.string.summary),
            style = MaterialTheme.typography.headlineLarge,
            color = White
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

                repeat(resultSummaryUiModel.correctCount) {
                    DrawStarCorrect()
                }

                repeat(resultSummaryUiModel.allCount - resultSummaryUiModel.correctCount) {
                    DrawStarIncorrect()
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = stringResource(
                    id = CoreR.string.correct_from_all,
                    resultSummaryUiModel.correctCount,
                    resultSummaryUiModel.allCount
                ),
                color = MaterialTheme.colorScheme.onSurface
            )



            Spacer(
                modifier = Modifier.height(24.dp)
            )

            SummaryMessage(resultSummaryUiModel)

        }

        Text(
          text =   stringResource(CoreR.string.your_answers),
            modifier = Modifier.padding(top = 36.dp),
            color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge
        )

        resultQuestionUiModel.forEachIndexed { index, model ->
            QuestionView(index, model)
        }

        MainButton(
            onClick = onStartAgainClick,
            text = stringResource(CoreR.string.start_again),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary,
        )

    }

}

@Composable
fun SummaryMessage(resultSummaryUiModel: ResultSummaryUiModel) {
    val summaryTitle = getSummaryTitle(resultSummaryUiModel.correctCount)
    val summaryMessage = getSummaryMessage(resultSummaryUiModel.correctCount)
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
fun ColumnScope.Answers(
    answerOption: String,
    userAnswer: String,
    isRight: Boolean,
) {

    val isSelected = userAnswer.contains(answerOption)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 16.dp)
            .then(
                if (isSelected) {
                    val borderColor =
                        if (isRight) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onTertiary

                    Modifier.border(
                        width = 1.dp,
                        shape = ShapeDefaults.Medium,
                        brush = SolidColor(borderColor),
                    )
                } else Modifier
            )
            .background(
                color = MaterialTheme.colorScheme.onBackground,
                shape = ShapeDefaults.Medium
            )
            .padding(16.dp)

    ) {
        if (isSelected) {
            var tintColor = MaterialTheme.colorScheme.onSecondary
            val isRightVector = if (isRight) {
                Icons.Filled.CheckCircle
            } else {
                tintColor = MaterialTheme.colorScheme.onTertiary
                Icons.Default.AddCircle
            }
            Icon(
                imageVector = isRightVector,
                contentDescription = null,
                tint = tintColor,
                modifier = Modifier
                    .size(20.dp)
                    .then(
                        if (!isRight)
                            Modifier.rotate(45.0F) else Modifier
                    )
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.radio_button_icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        Text(
            AnnotatedString.fromHtml(answerOption),
        )

    }

}
@Composable
fun QuestionView(
    questionIndex: Int,
    resultQuestionUiModel: ResultQuestionUiModel
) {
    Column(
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)

            .background(
                color = Color.White,
                shape = ShapeDefaults.ExtraLarge
            )
            .padding(bottom = 24.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(
                CoreR.string.question_number,
                questionIndex + 1
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp, bottom = 24.dp),
            color = MaterialTheme.colorScheme.surface
        )

        Text(
            text = AnnotatedString.fromHtml(resultQuestionUiModel.questionItem.question),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 8.dp),
            style = MaterialTheme.typography.titleLarge,
        )

        resultQuestionUiModel.questionItem.answers.forEach {
            Answers(
                answerOption = it,
                userAnswer = resultQuestionUiModel.selectedAnswer,
                isRight = resultQuestionUiModel.isAnswerRight
            )
        }

    }
}