package com.techyourchance.dagger2course.common.dependencyinjection

import android.view.LayoutInflater
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity

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