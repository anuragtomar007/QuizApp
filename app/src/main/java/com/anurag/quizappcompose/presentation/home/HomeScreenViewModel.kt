package com.anurag.quizappcompose.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {
    private val _homeState = MutableStateFlow(StateHomeScreen())
    val homeState = _homeState

    fun onEvent(event: EventHomeScreen) {
        when (event) {

            is EventHomeScreen.SetNumberOfQuizzes -> {
                _homeState.value = homeState.value.copy(numberOfQuizzes = event.numberOfQuizzes)
            }

            is EventHomeScreen.SetQuizCategory -> {
                _homeState.value = homeState.value.copy(quizCategory = event.category)
            }

            is EventHomeScreen.SetQuizDifficulty -> {
                _homeState.value = homeState.value.copy(quizDifficulty = event.difficulty)
            }

            is EventHomeScreen.SetQuizType -> {
                _homeState.value = homeState.value.copy(quizType = event.type)
            }
        }
    }
}