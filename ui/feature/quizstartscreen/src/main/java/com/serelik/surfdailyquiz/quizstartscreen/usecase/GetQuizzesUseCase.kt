package com.serelik.surfdailyquiz.quizstartscreen.usecase

import com.serelik.surfdailyquiz.domain.models.QuestionItem
import com.serelik.surfdailyquiz.domain.repository.QuizCacheRepository
import com.serelik.surfdailyquiz.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizzesUseCase @Inject constructor(
    private val databaseRepository: QuizCacheRepository,
    private val networkRepository: QuizRepository
) {

    suspend fun loadQuizzes(): List<QuestionItem> {
        val result = networkRepository.getQuiz()

        result.map { databaseRepository.addQuiz(it) }

        return result
    }
}