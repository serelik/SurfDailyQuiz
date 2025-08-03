package com.serelik.surfdailyquiz.ui.feature.history


import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.serelik.surfdailyquiz.domain.models.HistoryListItem
import java.time.format.DateTimeFormatter


@Composable
fun HistoryListScreen() {

    val viewModel: HistoryViewmodel = hiltViewModel()

    val historyState = viewModel.historyFlow.collectAsStateWithLifecycle(emptyList())

    val insets = WindowInsets.systemBars.asPaddingValues()

    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxHeight()
            .padding(insets),


        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Text(
            stringResource(R.string.history_title),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 38.dp, bottom = 40.dp),
            color = MaterialTheme.colorScheme.primary
        )

        if (historyState.value.isEmpty()) {
            HistoryEmptyScreen()
        } else
            LazyColumn {
                items(historyState.value) { item ->
                    QuizItemUi(item, viewModel::removeFromHistory)
                }
            }


    }
}

private val localDateFormatter = DateTimeFormatter.ofPattern("dd MMMM")
private val localTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

@Composable
fun QuizItemUi(historyListItem: HistoryListItem, onLongClick: (id: Long) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .background(
                color = Color.White,
                shape = ShapeDefaults.ExtraLarge
            )
            .padding(horizontal = 24.dp)
            .combinedClickable(
                onLongClick = { onLongClick.invoke(historyListItem.id) },
                onClick = {}
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(
                    R.string.quiz_count,
                    historyListItem.id
                ),
                style = MaterialTheme.typography.bodyLarge
            )

            Stars(historyListItem.correctAnswersCount)

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                localDateFormatter.format(historyListItem.timestamp),
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                localTimeFormatter.format(historyListItem.timestamp),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun DrawStar(isFilled: Boolean) {
    Icon(
        painterResource(com.serelik.core_n.R.drawable.star_icon),
        contentDescription = null,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .size(16.dp),
        tint = if (isFilled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.tertiary
    )
}

@Composable
fun Stars(starCount: Int) {
    Row {
        repeat(5) {
            DrawStar(starCount > it)
        }
    }
}

@Composable
@Preview
fun Preview() {
    HistoryListScreen()
}
