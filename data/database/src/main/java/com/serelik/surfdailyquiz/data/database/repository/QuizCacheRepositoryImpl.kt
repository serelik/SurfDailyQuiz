package com.serelik.surfdailyquiz.data.database.repository

import com.serelik.surfdailyquiz.data.database.dao.QuizDao
import com.serelik.surfdailyquiz.data.database.mapper.HistoryListMapper
import com.serelik.surfdailyquiz.data.database.mapper.QuizEntityMapper
import com.serelik.surfdailyquiz.data.database.models.HistoryEntity
import com.serelik.surfdailyquiz.domain.models.HistoryListItem
import com.serelik.surfdailyquiz.domain.models.QuestionItem
import com.serelik.surfdailyquiz.domain.repository.QuizCacheRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuizCacheRepositoryImpl @Inject constructor(
    val dao: QuizDao,
    val mapper: QuizEntityMapper,
    val historyMapper: HistoryListMapper
) : QuizCacheRepository {

    override suspend fun addQuiz(quiz: QuestionItem): Long {
        return dao.addQuiz(mapper.toEntity(quiz))

    }

    override suspend fun getQuiz(id: List<Long>): List<QuestionItem> {
        return dao.getQuestionsByIds(id).map(mapper::fromEntity)
    }

    override suspend fun saveHistory(
        questionItem: List<QuestionItem>,
        answers: Map<String, String>,
        correctAnswersCount: Int
    ) {
        val userAnswers = questionItem.map { answers[it.question] }

        val entity = HistoryEntity(
            quizId = questionItem.map { it.id }.joinToString(),
            timestamp = System.currentTimeMillis(),
            userAnswers = userAnswers.joinToString(SEPARATOR),
            correctAnswersCount = correctAnswersCount
        )

        dao.addHistory(entity)
    }

    override fun getHistoryItem(): Flow<List<HistoryListItem>> {
        return dao.getQuizzesHistory().map { it.map(historyMapper::fromEntity) }
    }

    override suspend fun removeById(id: Long) {
        dao.deleteFromHistory(id)
    }

    companion object {
        const val SEPARATOR = "$%$"
    }

}