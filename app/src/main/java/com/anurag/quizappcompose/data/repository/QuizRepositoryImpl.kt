package com.anurag.quizappcompose.data.repository

import com.anurag.quizappcompose.data.dto.quizToQuizModel
import com.anurag.quizappcompose.data.remote.QuizApi
import com.anurag.quizappcompose.domain.model.QuizModel
import com.anurag.quizappcompose.domain.repository.QuizRepository

class QuizRepositoryImpl(
    private val quizApi: QuizApi
) : QuizRepository {

    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<QuizModel> {

        val quizzes = quizApi.getQuizzes(amount, category, difficulty, type).results
        return quizzes.map { quizToQuizModel(it) }
    }
}