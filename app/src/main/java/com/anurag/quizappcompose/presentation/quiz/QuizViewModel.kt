package com.anurag.quizappcompose.presentation.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anurag.quizappcompose.common.Resource
import com.anurag.quizappcompose.data.dto.Quiz
import com.anurag.quizappcompose.domain.model.QuizModel
import com.anurag.quizappcompose.domain.usecases.GetQuizzesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val getQuizzesUseCase: GetQuizzesUseCase) :
    ViewModel() {

    private val _quizList = MutableStateFlow(StateQuizScreen())
    val quizList = _quizList

    fun onEvent(event: EventQuizScreen) {
        when (event) {
            is EventQuizScreen.GetQuizzes -> {
                getQuizzes(
                    event.numOfQuizzes,
                    event.quizCategory,
                    event.quizDifficulty,
                    event.quizType
                )
            }

            is EventQuizScreen.SetOptionSelected -> {
                updateQuizStateList(
                    event.quizStateIndex,
                    event.selectedOptionIndex
                )
            }
        }
    }

    private fun updateQuizStateList(quizStateIndex: Int, selectedOptionIndex: Int) {
        val updatedQuizStateList = mutableListOf<QuizState>()
        quizList.value.quizState.forEachIndexed { index, quizState ->
            updatedQuizStateList.add(
                if (quizStateIndex == index) {
                    quizState.copy(selectedOption = selectedOptionIndex)
                } else quizState
            )
        }
        _quizList.value = quizList.value.copy(quizState = updatedQuizStateList)

        //Better way
//            val currentQuizStateList = quizList.value.quizState.toMutableList()
//            val quizStateToUpdate = currentQuizStateList[quizStateIndex]
//            currentQuizStateList[quizStateIndex] = quizStateToUpdate.copy(selectedOption = selectedOptionIndex)
//
//            _quizList.value = quizList.value.copy(quizState = currentQuizStateList)

        updateScore(_quizList.value.quizState[quizStateIndex])
    }

    private fun updateScore(quizState: QuizState) {
        if (quizState.selectedOption != -1) {
            val correctAnswer = quizState.quiz?.correct_answer
            val selectedAnswer = quizState.selectedOption?.let {
                quizState.shuffledOptions[it].replace("&quot;", "\"").replace("&#039;", "\'")
            }
            Log.d("Quiz Detail", "$correctAnswer --> $selectedAnswer")
            if (correctAnswer == selectedAnswer) {
                val previousScore = _quizList.value.score
                _quizList.value = quizList.value.copy(score = previousScore + 1)
                Log.d("Quiz Detail", "Previous Score: $previousScore")
            }
        }
    }

    private fun getQuizzes(amount: Int, category: Int, difficulty: String, type: String) {
        viewModelScope.launch {
            getQuizzesUseCase(amount, category, difficulty, type).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _quizList.value = StateQuizScreen(isLoading = true)
                    }

                    is Resource.Success -> {
                        val listOfQuizState: List<QuizState> = getListOfQuizState(resource.data)
                        _quizList.value = StateQuizScreen(quizState = listOfQuizState)
                    }

                    is Resource.Error -> {
                        _quizList.value =
                            StateQuizScreen(error = resource.message ?: "Something went wrong")
                    }
                }
            }
        }
    }

    private fun getListOfQuizState(data: List<QuizModel>?): List<QuizState> {
        val listOfQuizState = mutableListOf<QuizState>()

        for (quiz in data!!) {
            val shuffledOptions = mutableListOf<String>().apply {
                add(quiz.correct_answer)
                addAll(quiz.incorrect_answers)
                shuffle()
            }
            listOfQuizState.add(QuizState(quiz, shuffledOptions, -1))
        }

        return listOfQuizState
    }

}