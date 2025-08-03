package com.serelik.surfdailyquiz.data.database.repository

import com.serelik.surfdailyquiz.data.database.dao.QuizDao
import com.serelik.surfdailyquiz.data.database.mapper.QuizEntityMapper
import com.serelik.surfdailyquiz.domain.models.QuestionItem
import com.serelik.surfdailyquiz.domain.repository.QuizCacheRepository
import javax.inject.Inject

 class QuizCacheRepositoryImpl @Inject constructor(
    val dao: QuizDao,
    val mapper: QuizEntityMapper
) : QuizCacheRepository {

    override suspend fun addQuiz(quiz: QuestionItem): Int {
        dao.addQuiz(mapper.toEntity(quiz))
        return quiz.id
    }

    override suspend fun getQuiz(id: Int): QuestionItem {
       return mapper.fromEntity(dao.getQuiz(id))
    }

}