package com.anurag.quizappcompose.data.dto

data class QuizResponse(
    val responseCode: Int,
    val results: List<Quiz>
)