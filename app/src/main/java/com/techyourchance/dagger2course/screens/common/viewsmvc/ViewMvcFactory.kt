package com.techyourchance.dagger2course.screens.common.viewsmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.techyourchance.dagger2course.screens.questionslist.QuestionListViewMvc

class ViewMvcFactory(
        private val layoutInflater: LayoutInflater
) {

    fun newQuestionListView(parent: ViewGroup?) =
            QuestionListViewMvc(layoutInflater, parent)

}