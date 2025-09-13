package com.serelik.results

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serelik.results.models.ResultQuestionUiModel
import com.serelik.results.models.ResultSummaryUiModel
import com.serelik.surfdailyquiz.domain.models.HistoryListItem
import com.serelik.surfdailyquiz.domain.models.QuestionItem
import com.serelik.surfdailyquiz.domain.repository.QuizCacheRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val repository: QuizCacheRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    val historyId = savedStateHandle.get<Long>(ID_KEY) ?: error("HistoryId must be not null")

    init {
        getHistoryItemById()
    }

    private val _resultStateFlow = MutableStateFlow<ResultState>(ResultState.Loading)

    val quizStateFlow = _resultStateFlow

    private var currentIndex = 0

    fun getHistoryItemById() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val historyListItem = repository.getHistoryItemById(historyId)
                val quiz = repository.getQuiz(historyListItem.quizzesIds)

                val resultQuizList = mutableListOf<ResultQuestionUiModel>()

                quiz.forEach {
                    resultQuizList.add(
                        createResultQuestionUiModel(
                            it,
                            historyListItem
                        )
                    )

                    currentIndex++
                }

                _resultStateFlow.emit(
                    ResultState.Result(
                        resultQuestionUiModel = resultQuizList,
                        resultSummaryUiModel = createResultSummaryUiModel(historyListItem)
                    )
                )
            }

        }


    }

    private fun createResultQuestionUiModel(
        questionItem: QuestionItem,
        historyListItem: HistoryListItem
    ): ResultQuestionUiModel {

        val model = ResultQuestionUiModel(
            questionNumber = currentIndex + 1,
            questionCount = historyListItem.questionsCount,
            questionItem = questionItem,
            selectedAnswer = historyListItem.userAnswer,
            isAnswerRight = historyListItem.userAnswer.contains(questionItem.correctAnswer)
        )

        return model
    }

    private fun createResultSummaryUiModel(historyListItem: HistoryListItem): ResultSummaryUiModel {

        return ResultSummaryUiModel(
            correctCount = historyListItem.correctAnswersCount,
            allCount = historyListItem.questionsCount
        )
    }
}