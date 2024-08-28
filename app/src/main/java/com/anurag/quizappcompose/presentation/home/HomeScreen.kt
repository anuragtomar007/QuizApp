package com.anurag.quizappcompose.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.anurag.quizappcompose.presentation.common.AppDropDownMenu
import com.anurag.quizappcompose.presentation.common.ButtonBox
import com.anurag.quizappcompose.presentation.home.component.HomeHeader
import com.anurag.quizappcompose.presentation.nav_graph.Routes
import com.anurag.quizappcompose.presentation.util.Constants
import com.anurag.quizappcompose.presentation.util.Constants.difficulty
import com.anurag.quizappcompose.presentation.util.Dimens.MediumPadding
import com.anurag.quizappcompose.presentation.util.Dimens.MediumSpacerHeight
import com.anurag.quizappcompose.presentation.util.Dimens.SmallSpacerHeight

@Composable
fun HomeScreen(
    state: StateHomeScreen = StateHomeScreen(),
    event: (EventHomeScreen) -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader()

        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        AppDropDownMenu(
            menuName = "Number of Questions:",
            menuList = Constants.numberAsString,
            text = state.numberOfQuizzes.toString(),
            onDropDownClick = {
                event(EventHomeScreen.SetNumberOfQuizzes(it.toInt()))
            }
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Category:",
            menuList = Constants.categories,
            text = state.quizCategory,
            onDropDownClick = {
                event(EventHomeScreen.SetQuizCategory(it))
            }
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Difficulty:",
            menuList = difficulty,
            text = state.quizDifficulty,
            onDropDownClick = {
                event(EventHomeScreen.SetQuizDifficulty(it))
            }
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Type:",
            menuList = Constants.type,
            text = state.quizType,
            onDropDownClick = {
                event(EventHomeScreen.SetQuizType(it))
            }
        )

        Spacer(modifier = Modifier.height(MediumSpacerHeight))

        ButtonBox(text = "Generate Quiz", padding = MediumPadding) {
            Log.d(
                "Quiz Detail",
                "${state.numberOfQuizzes} ${state.quizCategory} ${state.quizDifficulty} ${state.quizType}"
            )

            navController.navigate(
                route = Routes.QuizScreen.passQuizParams(
                    state.numberOfQuizzes,
                    state.quizCategory,
                    state.quizDifficulty,
                    state.quizType
                )
            )
        }
    }
}