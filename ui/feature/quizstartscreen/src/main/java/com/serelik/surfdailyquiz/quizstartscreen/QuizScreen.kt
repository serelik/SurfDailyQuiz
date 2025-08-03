package com.serelik.surfdailyquiz.quizstartscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serelik.quizstartscreen.R
import com.serelik.surfdailyquiz.domain.models.QuestionItem
import com.serelik.surfdailyquiz.quizstartscreen.models.QuestionUiModel
import com.serelik.surfdailyquiz.ui.theme.SurfDailyQuizTheme


@Composable
fun QuizScreen(
    questionUiModel: QuestionUiModel,
    onNextClick: () -> Unit = {},
    onSelectClick: (answer: String) -> Unit = {}
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(top = 40.dp)
                .padding(horizontal = 20.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_back_icon),
                contentDescription = "history Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 16.dp)
            )

            Icon(
                painter = painterResource(R.drawable.title_icon),
                contentDescription = "history Icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }

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
            Text(
                text = stringResource(R.string.question_number, questionUiModel.questionNumber),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp, bottom = 24.dp),
                color = MaterialTheme.colorScheme.surface
            )

            Text(
                text = AnnotatedString.fromHtml(questionUiModel.questionItem.question),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 8.dp),
                style = MaterialTheme.typography.titleLarge,
            )

            questionUiModel.questionItem.answers.forEach {
                AnswerOption(
                    it,
                    selected = questionUiModel.selectAnswer == it,
                    onSelectClick
                )
            }


            Button(
                onClick = { onNextClick.invoke() },
                enabled = questionUiModel.selectAnswer != null,
                shape = ShapeDefaults.Medium,
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                    disabledContentColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 24.dp)
                    .requiredSize(width = 260.dp, height = 50.dp)

            ) {
                Text(
                    text = stringResource(R.string.forward),
                )
            }

        }

    }

    Text(
        stringResource(R.string.warning_message),
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(vertical = 16.dp)
        ,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.labelSmall,
        textAlign = TextAlign.Center
    )

}

@Composable
fun ColumnScope.AnswerOption(
    answerOption: String, selected: Boolean,
    onSelectClick: (answer: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 16.dp)
            .then(
                if (selected) {
                    Modifier.border(
                        width = 1.dp,
                        shape = ShapeDefaults.Medium,
                        brush = SolidColor(MaterialTheme.colorScheme.secondary),
                    )
                } else Modifier
            )
            .clickable {
                onSelectClick.invoke(answerOption)
            }
            .background(
                color = MaterialTheme.colorScheme.onBackground,
                shape = ShapeDefaults.Medium
            )
            .padding(16.dp)

    ) {
        if (selected) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.unselected_button),
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


@Preview
@Composable
fun Preview() {
    SurfDailyQuizTheme {
        QuizScreen(
            QuestionUiModel(
                questionNumber = 2,
                questionCount = 5,
                questionItem = QuestionItem(
                    id = -1,
                    question = "Какое слово означает цвет?",
                    correctAnswer = "Груша",
                    incorrectAnswers = listOf("Яблоко", "Апельсин", "Ананас"),
                    type = "",
                    category = "",
                    difficulty = ""
                ),
                selectAnswer = "Яблоко"
            ),

            )
    }
}
