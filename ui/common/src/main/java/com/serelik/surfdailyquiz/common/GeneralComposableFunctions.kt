package com.serelik.surfdailyquiz.common


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.serelik.core_n.R as CoreR

@Composable
fun DrawStarCorrect(size: Dp = 52.dp) {
    Icon(
        painterResource(CoreR.drawable.star_icon),
        contentDescription = null,
        modifier = Modifier.padding(horizontal = 4.dp)
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