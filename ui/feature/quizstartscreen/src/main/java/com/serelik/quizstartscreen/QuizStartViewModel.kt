package com.serelik.quizstartscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serelik.surfdailyquiz.domain.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizStartViewModel @Inject constructor(
    val repository: QuizRepository
):ViewModel() {
    private val _quizStateFlow = MutableStateFlow<QuizState>(QuizState.NotStarted)

    val quizStateFlow = _quizStateFlow

    fun onStartQuizClick() {
        viewModelScope.launch {
            _quizStateFlow.emit(QuizState.Loading)
            try {
               // _quizStateFlow.emit(QuizState.Result(repository.getQuiz()))
                throw IllegalArgumentException()
            } catch (_: Exception) {
                _quizStateFlow.emit(QuizState.Error)
            }
        }
    }
}