package com.serelik.surfdailyquiz.common


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.serelik.surfdailyquiz.common.models.ResultSummaryUiModel
import com.serelik.core_n.R as CoreR

@Composable
fun DrawStarCorrect(size: Dp = 52.dp) {
    Icon(
        painterResource(CoreR.drawable.star_icon),
        contentDescription = null,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .size(size = size),
        tint = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun DrawStarIncorrect(size: Dp = 52.dp) {
    Icon(
        painterResource(CoreR.drawable.star_icon),
        contentDescription = null,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .size(size),
        tint = MaterialTheme.colorScheme.tertiary
    )
}

@Composable
fun getSummaryTitle(count: Int): String {
    return when (count) {
        0 -> stringResource(CoreR.string.summary_Title_0)
        1 -> stringResource(CoreR.string.summary_Title_1)
        2 -> stringResource(CoreR.string.summary_Title_2)
        3 -> stringResource(CoreR.string.summary_Title_3)
        4 -> stringResource(CoreR.string.summary_Title_4)
        5 -> stringResource(CoreR.string.summary_Title_5)
        else -> stringResource(CoreR.string.exception_message)
    }
}

@Composable
fun getSummaryMessage(count: Int): String {
    return when (count) {
        0 -> stringResource(CoreR.string.summary_message_0)
        1 -> stringResource(CoreR.string.summary_message_1)
        2 -> stringResource(CoreR.string.summary_message_2)
        3 -> stringResource(CoreR.string.summary_message_3)
        4 -> stringResource(CoreR.string.summary_message_4)
        5 -> stringResource(CoreR.string.summary_message_5)
        else -> stringResource(CoreR.string.exception_message)
    }
}

@Composable
fun MainButton(
    onClick: () -> Unit,
    text: String,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    isEnabled: Boolean = true,
    paddingTop: Dp = 52.dp,
    paddingBottom: Dp = 24.dp
) {
    Button(
        onClick = onClick,
        shape = ShapeDefaults.Medium,
        enabled = isEnabled,
        colors = ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = MaterialTheme.colorScheme.tertiary,
            disabledContentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .padding(top = paddingTop, bottom = paddingBottom)
            .requiredSize(width = 260.dp, height = 50.dp)

    ) {
        Text(
            text = text,
        )
    }
}

@Composable
fun LoaderView() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun SummaryMessage(finishUiModel: ResultSummaryUiModel) {
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