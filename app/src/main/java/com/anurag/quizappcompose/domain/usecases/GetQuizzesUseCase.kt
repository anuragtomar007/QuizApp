package com.anurag.quizappcompose.domain.usecases

import com.anurag.quizappcompose.common.Resource
import com.anurag.quizappcompose.data.dto.Quiz
import com.anurag.quizappcompose.domain.model.QuizModel
import com.anurag.quizappcompose.domain.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetQuizzesUseCase(
    private val repository: QuizRepository,
) {
    operator fun invoke(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): Flow<Resource<List<QuizModel>>> = flow {

        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getQuizzes(amount, category, difficulty, type)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)
}