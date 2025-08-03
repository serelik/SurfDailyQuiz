package com.serelik.surfdailyquiz.quizstartscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serelik.surfdailyquiz.quizstartscreen.usecase.GetQuizzesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizStartViewModel @Inject constructor(
   private val useCase: GetQuizzesUseCase
):ViewModel() {
    private val _quizStateFlow = MutableStateFlow<QuizState>(QuizState.NotStarted)

    val quizStateFlow = _quizStateFlow

    fun onStartQuizClick() {
        viewModelScope.launch {
            _quizStateFlow.emit(QuizState.Loading)
            try {
                _quizStateFlow.emit(QuizState.Result(useCase.loadQuizzes()))
            } catch (_: Exception) {
                _quizStateFlow.emit(QuizState.Error)
            }
        }
    }
}