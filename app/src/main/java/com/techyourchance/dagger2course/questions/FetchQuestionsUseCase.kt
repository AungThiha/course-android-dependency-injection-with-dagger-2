package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase.Result.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchQuestionsUseCase(
        private val stackoverflowApi: StackoverflowApi
) {

    sealed class Result {
        data class Success(val questions: List<Question>) : Result()
        object Failure: Result()
    }

    suspend fun fetchLatestQuestions(): Result = withContext(Dispatchers.IO) {
        try {
            val response = stackoverflowApi.lastActiveQuestions(20)
            if (response.isSuccessful && response.body() != null) {
                Success(response.body()!!.questions)
            } else {
                Failure
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                Failure
            } else {
                throw t
            }
        }
    }
}