package com.serelik.surfdailyquiz.data.database.mapper

import com.serelik.surfdailyquiz.data.database.models.HistoryEntity
import com.serelik.surfdailyquiz.domain.models.HistoryListItem
import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone
import javax.inject.Inject

class HistoryListMapper @Inject constructor() {

    fun fromEntity(quizEntity: HistoryEntity): HistoryListItem {

        return HistoryListItem(
            id = quizEntity.id?: -1,
            correctAnswersCount = quizEntity.correctAnswersCount,
            questionsCount = quizEntity.quizId.split(", ").size,
            timestamp = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(quizEntity.timestamp), TimeZone
                    .getDefault().toZoneId()
            )
        )
    }
}