package com.techyourchance.dagger2course.common.dependencyinjection.activity

import android.view.LayoutInflater
import com.techyourchance.dagger2course.common.dependencyinjection.app.AppComponent
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
        private val activity: BaseActivity
) {

    @Provides
    fun activity() = activity

    @Provides
    fun fragmentManager() = activity.supportFragmentManager

    @Provides
    fun layoutInflater() = LayoutInflater.from(activity)

}