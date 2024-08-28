package com.anurag.quizappcompose.presentation.quiz

import com.anurag.quizappcompose.domain.model.QuizModel

data class StateQuizScreen(
    val isLoading: Boolean = false,
    val quizState: List<QuizState> = listOf(),
    val error: String = "",
    val score: Int = 0
)

data class QuizState(
    val quiz: QuizModel? = null,
    val shuffledOptions: List<String> = emptyList(),
    val selectedOption: Int? = -1,
)