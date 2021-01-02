package com.techyourchance.dagger2course.common.composition

import android.view.LayoutInflater
import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.viewsmvc.ViewMvcFactory

class ActivityCompositionRoot(
        private val activity: BaseActivity,
        private val appCompositionRoot: AppCompositionRoot
) {

    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    private val fragmentManager
        get() = activity.supportFragmentManager

    val dialogNavigator
        get() = DialogsNavigator(fragmentManager)

    private val layoutInflater
        get() = LayoutInflater.from(activity)

    val viewMvcFactory
        get() = ViewMvcFactory(layoutInflater)

    private val stackoverflowApi = appCompositionRoot.stackoverflowApi

    val fetchQuestionsUseCase: FetchQuestionsUseCase
        get() = FetchQuestionsUseCase(stackoverflowApi)

    val fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase
        get() = FetchQuestionDetailsUseCase(stackoverflowApi)

}