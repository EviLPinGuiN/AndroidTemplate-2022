package com.itis.template.presentation.mvvm.main

import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: MainMvvmActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainComponent
    }
}