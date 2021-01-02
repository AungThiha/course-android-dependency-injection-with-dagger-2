package com.techyourchance.dagger2course.common.composition

import android.view.LayoutInflater
import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.viewsmvc.ViewMvcFactory

class ActivityCompositionRoot(
        val activity: BaseActivity,
        private val appCompositionRoot: AppCompositionRoot
) {

    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    val application
        get() = appCompositionRoot.application

    val fragmentManager
        get() = activity.supportFragmentManager

    val layoutInflater: LayoutInflater
        get() = LayoutInflater.from(activity)

    val stackoverflowApi = appCompositionRoot.stackoverflowApi

}