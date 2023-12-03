package com.example.postsapplication.framework.di

import com.example.postsapplication.framework.viewModel.BaseAndroidViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class, RemoteUseCasesModule::class])
fun interface ViewModelComponent {
    fun inject(baseAndroidViewModel: BaseAndroidViewModel)
}