package com.serelik.surfdailyquiz.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = DbContract.Quizzes.TABLE_NAME
)
data class QuizEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.Quizzes.COLUMN_NAME_ID)
    val id: Long?,
    @ColumnInfo(name = DbContract.Quizzes.COLUMN_NAME_TYPE)
    val type: String,
    @ColumnInfo(name = DbContract.Quizzes.COLUMN_NAME_DIFFICULTY)
    val difficulty: String,
    @ColumnInfo(name = DbContract.Quizzes.COLUMN_NAME_QUESTION)
    val question: String,
    @ColumnInfo(name = DbContract.Quizzes.COLUMN_NAME_CORRECT_ANSWER)
    val correctAnswer: String,
    @ColumnInfo(name = DbContract.Quizzes.COLUMN_NAME_CATERGORY)
    val category: String,
    @ColumnInfo(name = DbContract.Quizzes.COLUMN_NAME_INCORRECT_ANSWERS)
    val incorrectAnswers: String,
)
