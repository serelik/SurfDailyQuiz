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

    override suspend fun addQuiz(quiz: QuestionItem): Long {
        return dao.addQuiz(mapper.toEntity(quiz))

    }

    override suspend fun getQuiz(id: Long): QuestionItem {
       return mapper.fromEntity(dao.getQuiz(id))
    }

}