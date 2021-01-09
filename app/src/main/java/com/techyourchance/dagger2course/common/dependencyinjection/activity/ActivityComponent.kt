package com.techyourchance.dagger2course.common.dependencyinjection.activity

import android.app.Application
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.common.dependencyinjection.app.AppComponent
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun activity(): BaseActivity

    fun screensNavigator(): ScreensNavigator

    fun application(): Application

    fun fragmentManager(): FragmentManager

    fun layoutInflater(): LayoutInflater

    fun stackoverflowApi(): StackoverflowApi

}