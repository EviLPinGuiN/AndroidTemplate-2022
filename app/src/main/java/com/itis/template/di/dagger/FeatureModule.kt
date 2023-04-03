package com.itis.template.di.dagger

import com.itis.template.presentation.mvvm.main.MainModule
import com.itis.template.presentation.mvvm.main.MainMvvmActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(ActivityComponent::class)
interface FeatureModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun contributeMainActivity(): MainMvvmActivity
}