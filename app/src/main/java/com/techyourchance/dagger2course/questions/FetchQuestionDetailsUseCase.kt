package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase.Result.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchQuestionDetailsUseCase {

    sealed class Result {
        class Success(val questionWithBody: QuestionWithBody) : Result()
        object Failure: Result()
    }

    // init retrofit
    private val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val stackoverflowApi: StackoverflowApi = retrofit.create(StackoverflowApi::class.java)

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