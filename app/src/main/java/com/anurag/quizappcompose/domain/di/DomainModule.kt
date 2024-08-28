package com.anurag.quizappcompose.domain.di

import com.anurag.quizappcompose.domain.repository.QuizRepository
import com.anurag.quizappcompose.domain.usecases.GetQuizzesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetQuizzesUseCase(quizRepository: QuizRepository): GetQuizzesUseCase {
        return GetQuizzesUseCase(quizRepository)
    }
}