package com.anurag.quizappcompose.presentation.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.anurag.quizappcompose.presentation.home.HomeScreen
import com.anurag.quizappcompose.presentation.home.HomeScreenViewModel
import com.anurag.quizappcompose.presentation.quiz.QuizScreen
import com.anurag.quizappcompose.presentation.quiz.QuizViewModel
import com.anurag.quizappcompose.presentation.score.ScoreScreen

@Composable
fun SetNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
        composable(route = Routes.HomeScreen.route) {
            val viewModel: HomeScreenViewModel = hiltViewModel()
            val state by viewModel.homeState.collectAsState()
            HomeScreen(
                state = state,
                event = viewModel::onEvent,
                navController = navController
            )
        }

        composable(route = Routes.QuizScreen.route,
            arguments = listOf(
                navArgument(ARG_KEY_QUIZ_NUMBER) {
                    type = NavType.IntType
                },
                navArgument(ARG_KEY_QUIZ_CATEGORY) {
                    type = NavType.StringType
                },
                navArgument(ARG_KEY_QUIZ_DIFFICULTY) {
                    type = NavType.StringType
                },
                navArgument(ARG_KEY_QUIZ_TYPE) {
                    type = NavType.StringType
                }
            )
        ) {
            val numOfQuizzes = it.arguments?.getInt(ARG_KEY_QUIZ_NUMBER)
            val category = it.arguments?.getString(ARG_KEY_QUIZ_CATEGORY)
            val difficulty = it.arguments?.getString(ARG_KEY_QUIZ_DIFFICULTY)
            val type = it.arguments?.getString(ARG_KEY_QUIZ_TYPE)

            val quizViewModel: QuizViewModel = hiltViewModel()
            val state by quizViewModel.quizList.collectAsState()
            QuizScreen(
                numberOfQuiz = numOfQuizzes!!,
                quizCategory = category!!,
                quizDifficulty = difficulty!!,
                quizType = type!!,
                event = quizViewModel::onEvent,
                state = state,
                navController = navController
            )
        }

        composable(
            route = Routes.ScoreScreen.route,
            arguments = listOf(
                navArgument(ARG_KEY_NUM_OF_QUE) {
                    type = NavType.IntType
                },
                navArgument(ARG_KEY_CORRECT_ANSWER) {
                    type = NavType.IntType
                },
            )
        ) {
            val numOfQuestions = it.arguments?.getInt(ARG_KEY_NUM_OF_QUE)
            val numOfCorrectAnswers = it.arguments?.getInt(ARG_KEY_CORRECT_ANSWER)

            ScoreScreen(
                numOfQuestions = numOfQuestions!!,
                numOfCorrectAnswers = numOfCorrectAnswers!!,
                navController = navController
            )
        }
    }
}