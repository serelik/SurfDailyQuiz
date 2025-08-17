package com.serelik.results

import com.serelik.results.models.ResultQuestionUiModel
import com.serelik.results.models.ResultSummaryUiModel

sealed interface ResultState {
    data object Loading : ResultState

    data class Result(
        val resultQuestionUiModel: List<ResultQuestionUiModel>,
        val resultSummaryUiModel: ResultSummaryUiModel
    ) : ResultState
}