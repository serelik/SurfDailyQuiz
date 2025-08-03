package com.serelik.surfdailyquiz.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = DbContract.History.TABLE_NAME
)
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.History.COLUMN_NAME_ID)
    val id: Long? = null,

    @ColumnInfo(name = DbContract.History.COLUMN_NAME_QUIZZES_ID)
    val quizId: String,

    @ColumnInfo(name = DbContract.History.COLUMN_NAME_TIMESTAMP)
    val timestamp: Long,

    @ColumnInfo(name = DbContract.History.COLUMN_NAME_ANSWERS)
    val userAnswers: String,

    @ColumnInfo(name = DbContract.History.COLUMN_NAME_CORRECT_ANSWERS_COUNT)
    val correctAnswersCount: Int
)
