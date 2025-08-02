package com.serelik.surfdailyquiz.network

import com.serelik.surfdailyquiz.network.models.QuizzesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {
    @GET("api.php?amount=5")
    suspend fun getQuiz(
        @Query("type") type: String = "multiple",
        @Query("category") category: String = "9",
        @Query("difficulty") difficulty: String = "easy"
    ): QuizzesResponse
}