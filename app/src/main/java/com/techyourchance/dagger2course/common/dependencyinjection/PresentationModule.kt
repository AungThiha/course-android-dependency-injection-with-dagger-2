package com.techyourchance.dagger2course.common.dependencyinjection

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.viewsmvc.ViewMvcFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(
        private val activityCompositionRoot: ActivityCompositionRoot
) {

    @Provides
    fun activity() = activityCompositionRoot.activity

    @Provides
    fun fragmentManager() = activityCompositionRoot.fragmentManager

    @Provides
    fun layoutInflater() = activityCompositionRoot.layoutInflater

    @Provides
    fun stackoverflowApi() = activityCompositionRoot.stackoverflowApi

    @Provides
    fun screensNavigator() = activityCompositionRoot.screensNavigator

    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) =
            ViewMvcFactory(layoutInflater)

    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) =
            DialogsNavigator(fragmentManager)

    @Provides
    fun fetchQuestionsUseCase(stackoverflowApi: StackoverflowApi) =
            FetchQuestionsUseCase(stackoverflowApi)

    @Provides
    fun fetchQuestionDetailsUseCase(stackoverflowApi: StackoverflowApi) =
            FetchQuestionDetailsUseCase(stackoverflowApi)

}