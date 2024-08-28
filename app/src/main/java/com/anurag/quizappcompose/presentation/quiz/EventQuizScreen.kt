package com.anurag.quizappcompose.presentation.quiz

sealed class EventQuizScreen {

    data class GetQuizzes(
        val numOfQuizzes: Int,
        val quizCategory: Int,
        val quizDifficulty: String,
        val quizType: String
    ) : EventQuizScreen()

    data class SetOptionSelected(val quizStateIndex: Int, val selectedOptionIndex: Int) : EventQuizScreen()
}
