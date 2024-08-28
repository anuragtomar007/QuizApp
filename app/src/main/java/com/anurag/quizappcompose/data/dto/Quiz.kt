package com.anurag.quizappcompose.data.dto

import com.anurag.quizappcompose.domain.model.QuizModel

data class Quiz(
    val category: String,
    val correct_answer: String,
    val difficulty: String,
    val incorrect_answers: List<String>,
    val question: String,
    val type: String
)

fun quizToQuizModel(quiz: Quiz): QuizModel {
    return QuizModel(
        category = quiz.category,
        correct_answer = quiz.correct_answer,
        difficulty = quiz.difficulty,
        incorrect_answers = quiz.incorrect_answers,
        question = quiz.question,
        type = quiz.type
    )
}