package com.anurag.quizappcompose.presentation.nav_graph

const val ARG_KEY_QUIZ_NUMBER = "ak_quiz_number"
const val ARG_KEY_QUIZ_CATEGORY = "ak_quiz_category"
const val ARG_KEY_QUIZ_DIFFICULTY = "ak_quiz_difficulty"
const val ARG_KEY_QUIZ_TYPE = "ak_quiz_type"
const val ARG_KEY_NUM_OF_QUE = "ak_num_of_questions"
const val ARG_KEY_CORRECT_ANSWER = "ak_correct_answer"

sealed class Routes(val route: String) {

    object HomeScreen : Routes("home_screen")

    object QuizScreen :
        Routes("quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}") {

        fun passQuizParams(
            numOfQuizzes: Int,
            category: String,
            difficulty: String,
            type: String
        ): String {
            return "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}"
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_NUMBER}",
                    newValue = numOfQuizzes.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_CATEGORY}",
                    newValue = category
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_DIFFICULTY}",
                    newValue = difficulty
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_TYPE}",
                    newValue = type
                )
        }
    }

    object ScoreScreen : Routes("score_screen/{$ARG_KEY_NUM_OF_QUE}/{$ARG_KEY_CORRECT_ANSWER}") {
        fun passScoreParams(questions: Int, answers: Int): String {
            return "score_screen/{$ARG_KEY_NUM_OF_QUE}/{$ARG_KEY_CORRECT_ANSWER}"
                .replace(
                    oldValue = "{$ARG_KEY_NUM_OF_QUE}",
                    newValue = questions.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_CORRECT_ANSWER}",
                    newValue = answers.toString()
                )
        }
    }
}
