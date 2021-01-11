package com.techyourchance.dagger2course.screens.common.fragments

import androidx.fragment.app.Fragment
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationComponent
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity

abstract class BaseFragment : Fragment() {

    private val presentationComponent: PresentationComponent by lazy {
        ((requireActivity() as BaseActivity).activityComponent)
                .newPresentationComponent()
    }

    protected val injector get() = presentationComponent

}