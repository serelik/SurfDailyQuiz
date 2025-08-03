package com.serelik.surfdailyquiz.quizstartscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serelik.surfdailyquiz.domain.models.QuestionItem
import com.serelik.surfdailyquiz.quizstartscreen.models.QuestionUiModel
import com.serelik.surfdailyquiz.quizstartscreen.usecase.GetQuizzesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizStartViewModel @Inject constructor(
    private val useCase: GetQuizzesUseCase
) : ViewModel() {
    private val _quizStateFlow = MutableStateFlow<QuizState>(QuizState.NotStarted)

    val quizStateFlow = _quizStateFlow

    private var quizItem: List<QuestionItem> = emptyList()

    private val selectAnswers = mutableMapOf<String, String>()

    private var currentIndex = 0

    fun onStartQuizClick() {
        viewModelScope.launch {
            _quizStateFlow.emit(QuizState.Loading)
            try {
                quizItem = useCase.loadQuizzes()
                _quizStateFlow.emit(QuizState.Quiz(createQuestionUiModel()))


            } catch (e: Exception) {
                e.printStackTrace()
                _quizStateFlow.emit(QuizState.Error)
            }
        }
    }

    private fun createQuestionUiModel(): QuestionUiModel {
        val question = quizItem[currentIndex]

        return QuestionUiModel(
            questionNumber = currentIndex + 1,
            questionCount = quizItem.size,
            questionItem = question,
            selectAnswer = selectAnswers[question.question]
        )
    }

    fun selectAnswer(answer: String) {

        val question = quizItem[currentIndex]

        selectAnswers[question.question] = answer

        viewModelScope.launch {
            _quizStateFlow.emit(QuizState.Quiz(createQuestionUiModel()))
        }
    }

    fun nextClick() {
        if (currentIndex + 1 == quizItem.size)
            calculateResult()
        else {
            currentIndex++
            viewModelScope.launch {
                _quizStateFlow.emit(QuizState.Quiz(createQuestionUiModel()))
            }
        }
    }

    fun calculateResult() {
        var correctAnswers = 0
        quizItem.forEach {
            if (it.correctAnswer == selectAnswers[it.question])
                correctAnswers++
        }

        val result = QuizState.QuizFinishUiModel(
            correctCount = correctAnswers,
            allCount = quizItem.size
        )
        viewModelScope.launch {
            _quizStateFlow.emit(result)
        }

    }

    fun onNewQuizClick() {
        selectAnswers.clear()
        currentIndex = 0
        quizItem = emptyList()
        viewModelScope.launch {
            _quizStateFlow.emit(QuizState.NotStarted)
        }
    }

}