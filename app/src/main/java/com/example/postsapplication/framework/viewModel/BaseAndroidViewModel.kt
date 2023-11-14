package com.example.postsapplication.framework.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.postsapplication.framework.UseCases
import com.example.postsapplication.framework.di.ApplicationModule
import com.example.postsapplication.framework.di.DaggerViewModelComponent
import com.example.postsapplication.framework.di.RepositoryModule
import com.example.postsapplication.framework.di.UseCasesModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import javax.inject.Inject

abstract class BaseAndroidViewModel(application: Application): AndroidViewModel(application) {
    protected val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    protected lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(application))
            .repositoryModule(RepositoryModule())
            .useCasesModule(UseCasesModule())
            .build()
            .inject(this)
    }

}