package com.serelik.surfdailyquiz.ui.feature.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
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

@Composable
fun HistoryEmptyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
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
                stringResource(R.string.history_empty_message),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(24.dp)
            )

            Button(
                onClick = { },
                shape = ShapeDefaults.Medium,
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                    disabledContentColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 24.dp, start = 30.dp, end = 30.dp)
                    .requiredSize(width = 260.dp, height = 50.dp)

            ) {
                Text(
                    text = stringResource(R.string.start_quiz),
                )
            }
        }

        Icon(
            painter = painterResource(R.drawable.title_icon),
            contentDescription = "history Icon",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp, start = 90.dp, end = 90.dp)
                .align(Alignment.BottomCenter),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
fun Previeww() {
    HistoryEmptyScreen()
}