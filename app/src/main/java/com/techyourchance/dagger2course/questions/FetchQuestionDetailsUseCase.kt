package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase.Result.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class FetchQuestionDetailsUseCase(
        private val stackoverflowApi: StackoverflowApi
) {

    sealed class Result {
        data class Success(val questionWithBody: QuestionWithBody) : Result()
        object Failure: Result()
    }

    suspend fun fetchQuestionDetails(questionId: String): Result = withContext(Dispatchers.IO) {
        try {
            val response = stackoverflowApi.questionDetails(questionId)
            if (response.isSuccessful && response.body() != null) {
                val questionWithBody = response.body()!!.question
                Success(questionWithBody)
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