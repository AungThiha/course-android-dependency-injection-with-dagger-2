package com.techyourchance.dagger2course.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.fragments.BaseFragment
import kotlinx.coroutines.*

class QuestionDetailsFragment : BaseFragment(), QuestionDetailsViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var questionId: String

    private lateinit var viewMvc: QuestionDetailsViewMvc

    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase

    private lateinit var dialogsNavigator: DialogsNavigator

    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchQuestionDetailsUseCase = compositionRoot.fetchQuestionDetailsUseCase

        dialogsNavigator = compositionRoot.dialogNavigator

        screensNavigator = compositionRoot.screensNavigator

        // retrieve question ID passed from outside
        questionId = arguments!!.getString(EXTRA_QUESTION_ID)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewMvc = compositionRoot.viewMvcFactory.newQuestionDetailsView(container)
        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onBackClicked() {
        screensNavigator.navigateBack()
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                when (val result = fetchQuestionDetailsUseCase.fetchQuestionDetails(questionId)) {
                    is FetchQuestionDetailsUseCase.Result.Success -> {
                        val questionBody = result.questionWithBody.body
                        viewMvc.bindQuestionBody(questionBody)
                    }
                    FetchQuestionDetailsUseCase.Result.Failure -> {
                        onFetchFailed()
                    }
                }
            } finally {
                viewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    companion object {

        private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

        fun newInstance(questionId: String) =
                QuestionDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString(EXTRA_QUESTION_ID, questionId)
                    }
                }

    }

}