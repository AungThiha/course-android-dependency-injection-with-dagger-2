package com.techyourchance.dagger2course.common.dependencyinjection

import android.view.LayoutInflater
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
        private val activity: BaseActivity,
        private val appComponent: AppComponent
) {

    private val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    @Provides
    fun activity() = activity

    @Provides
    fun screensNavigator() = screensNavigator

    @Provides
    fun application() = appComponent.application()

    @Provides
    fun fragmentManager() = activity.supportFragmentManager

    @Provides
    fun layoutInflater() = LayoutInflater.from(activity)

    @Provides
    fun stackoverflowApi() = appComponent.stackoverflowApi()

}