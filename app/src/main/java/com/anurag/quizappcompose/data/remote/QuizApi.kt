package com.anurag.quizappcompose.data.remote

import com.anurag.quizappcompose.data.dto.QuizResponse
import retrofit2.http.GET
import retrofit2.http.Query


//https://opentdb.com/api.php?amount=10&category=9&difficulty=easy&type=multiple
interface QuizApi {
    @GET("api.php")
    suspend fun getQuizzes(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String
    ): QuizResponse
}