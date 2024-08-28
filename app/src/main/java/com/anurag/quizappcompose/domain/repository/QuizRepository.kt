package com.anurag.quizappcompose.domain.repository

import com.anurag.quizappcompose.domain.model.QuizModel

interface QuizRepository {

    suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<QuizModel>
}