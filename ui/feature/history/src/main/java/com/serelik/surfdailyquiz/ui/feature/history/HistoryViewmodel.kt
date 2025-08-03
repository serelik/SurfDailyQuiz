package com.serelik.surfdailyquiz.ui.feature.history

import androidx.lifecycle.ViewModel
import com.serelik.surfdailyquiz.domain.repository.QuizCacheRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewmodel @Inject constructor(
    repository: QuizCacheRepository
) : ViewModel() {

    val historyFlow = repository.getHistoryItem()

}