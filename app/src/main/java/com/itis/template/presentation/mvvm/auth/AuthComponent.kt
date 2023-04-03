package com.itis.template.presentation.mvvm.auth

import com.itis.template.di.dagger.FeatureScope
import dagger.BindsInstance
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [AuthModule::class])
interface AuthComponent {

    fun inject(activity: AuthActivity)

//    fun plusSignInComponent(): SignInComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun setCityId(@ArgCityId id: Int): Builder
        fun build(): AuthComponent
    }
}
//
//@Subcomponent(modules = [SignInModule::class])
//interface SignInComponent {
//
//    fun inject(fragment: SignInFragment)
//
//    @Subcomponent.Builder
//    interface Builder {
//        fun build(): SignInComponent
//    }
//}