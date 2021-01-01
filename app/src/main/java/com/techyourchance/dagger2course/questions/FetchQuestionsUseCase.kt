package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase.Result.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchQuestionsUseCase {

    sealed class Result {
        class Success(val questions: List<Question>) : Result()
        object Failure: Result()
    }

    // init retrofit
    private val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val stackoverflowApi: StackoverflowApi = retrofit.create(StackoverflowApi::class.java)

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