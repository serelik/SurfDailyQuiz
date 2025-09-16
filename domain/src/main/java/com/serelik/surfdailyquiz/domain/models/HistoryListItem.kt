package com.serelik.surfdailyquiz.domain.models

import java.time.LocalDateTime

data class HistoryListItem(
    val id: Long,
    val quizzesIds: List<Long>,
    val correctAnswersCount: Int,
    val questionsCount: Int,
    val userAnswer: String,
    val timestamp: LocalDateTime,
)