package com.serelik.surfdailyquiz.domain.models

import java.time.LocalDateTime

data class HistoryListItem(
    val id: Long,
    val correctAnswersCount: Int,
    val questionsCount: Int,
    val timestamp: LocalDateTime,
)