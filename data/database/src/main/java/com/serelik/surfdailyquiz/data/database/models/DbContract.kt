package com.serelik.surfdailyquiz.data.database.models

import android.provider.BaseColumns

internal object DbContract {
    const val DATABASE_NAME = "quiz.db"

    object Quizzes {

        const val TABLE_NAME = "quizzes"

        const val COLUMN_NAME_ID = BaseColumns._ID

        const val COLUMN_NAME_TYPE = "type"

        const val COLUMN_NAME_DIFFICULTY = "difficulty"

        const val COLUMN_NAME_CATERGORY = "catergory"

        const val COLUMN_NAME_QUESTION = "question"

        const val COLUMN_NAME_CORRECT_ANSWER = "correct_answer"

        const val COLUMN_NAME_INCORRECT_ANSWERS = "incorrect_answers"
    }


    object History {
        const val TABLE_NAME = "history"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_QUIZZES_ID = "quiz_id"
        const val COLUMN_NAME_TIMESTAMP = "timestamp"

        const val COLUMN_NAME_ANSWERS = "answers"

        const val COLUMN_NAME_CORRECT_ANSWERS_COUNT = "correctAnswersCount"

    }

}
