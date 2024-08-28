package com.anurag.quizappcompose.presentation.home

data class StateHomeScreen(
    val numberOfQuizzes: Int = 10,
    val quizCategory: String = "General Knowledge",
    val quizDifficulty: String = "Easy",
    val quizType: String = "Multiple Choice"
)
