package com.serelik.surfdailyquiz.network.repository

import com.serelik.surfdailyquiz.domain.models.QuestionItem
import com.serelik.surfdailyquiz.domain.repository.QuizRepository
import com.serelik.surfdailyquiz.network.QuizApi
import com.serelik.surfdailyquiz.network.mappers.QuizResponseMapper
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizApi: QuizApi,
    val mapper: QuizResponseMapper
    ): QuizRepository {

    override suspend fun getQuiz(): List<QuestionItem> {
        val quizResponse=  quizApi.getQuiz()

        return mapper.convert(quizResponse)
    }

}