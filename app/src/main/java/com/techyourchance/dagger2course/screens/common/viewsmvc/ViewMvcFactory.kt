package com.techyourchance.dagger2course.screens.common.viewsmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsViewMvc
import com.techyourchance.dagger2course.screens.questionslist.QuestionListViewMvc
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(
        private val layoutInflater: LayoutInflater
) {

    fun newQuestionListView(parent: ViewGroup?) =
            QuestionListViewMvc(layoutInflater, parent)

    fun newQuestionDetailsView(parent: ViewGroup?) =
            QuestionDetailsViewMvc(layoutInflater, parent)

}