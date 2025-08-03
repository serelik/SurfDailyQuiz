package com.serelik.surfdailyquiz.ui.feature.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serelik.surfdailyquiz.domain.repository.QuizCacheRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewmodel @Inject constructor(
    private val repository: QuizCacheRepository
) : ViewModel() {

    val historyFlow = repository.getHistoryItem()

    fun removeFromHistory(id: Long) {
        viewModelScope.launch { repository.removeById(id) }
    }

}