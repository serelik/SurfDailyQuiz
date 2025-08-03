package com.serelik.surfdailyquiz.domain.models

data class HistoryListItem(
    val id: Long,
    val correctAnswersCount: Int,
    val questionsCount: Int,
    val timestamp: Long,
)