package com.serelik.surfdailyquiz.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizzesResponse(
    @SerialName("response_code")
    val responseCode: Int,
    @SerialName("results")
    val results: List<QuestionDto>
)