package com.serelik.surfdailyquiz.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.serelik.surfdailyquiz.data.database.dao.QuizDao
import com.serelik.surfdailyquiz.data.database.models.HistoryEntity
import com.serelik.surfdailyquiz.data.database.models.QuizEntity

@Database(
    entities = [QuizEntity::class, HistoryEntity::class],
    version = 1
)
 abstract class QuizDatabase: RoomDatabase() {

    abstract val quizDao: QuizDao
}