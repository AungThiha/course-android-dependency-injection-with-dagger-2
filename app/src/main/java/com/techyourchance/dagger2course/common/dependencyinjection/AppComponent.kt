package com.techyourchance.dagger2course.common.dependencyinjection

import android.app.Application
import com.techyourchance.dagger2course.networking.StackoverflowApi
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [AppModule::class])
interface AppComponent {

    fun application(): Application

    fun retrofit(): Retrofit

    fun stackoverflowApi(): StackoverflowApi

}