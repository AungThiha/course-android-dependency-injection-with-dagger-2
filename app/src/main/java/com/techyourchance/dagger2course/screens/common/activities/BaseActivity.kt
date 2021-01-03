package com.techyourchance.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.common.dependencyinjection.*

abstract class BaseActivity : AppCompatActivity() {

    private val appCompositionRoot
        get() = (application as MyApplication).appCompositionRoot

    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    private val presentationComponent: PresentationComponent by lazy {
        DaggerPresentationComponent.builder().presentationModule(
                PresentationModule(activityCompositionRoot)
        ).build()
    }

    val injector get() = Injector(presentationComponent)

}