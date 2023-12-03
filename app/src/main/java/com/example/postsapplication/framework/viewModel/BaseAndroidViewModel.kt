package com.example.postsapplication.framework.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.UseCases
import com.example.postsapplication.framework.RemoteUseCases
import com.example.postsapplication.framework.di.ApplicationModule
import com.example.postsapplication.framework.di.DaggerViewModelComponent
import com.example.postsapplication.framework.di.RemoteUseCasesModule
import com.example.postsapplication.framework.di.RepositoryModule
import com.example.postsapplication.framework.di.UseCasesModule
import com.example.postsapplication.presentation.utils.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import javax.inject.Inject

abstract class BaseAndroidViewModel (application: Application): AndroidViewModel(application) {

    @Inject
    protected lateinit var useCases: UseCases

    @Inject
    protected lateinit var remoteUseCases: RemoteUseCases

    protected val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(application))
            .repositoryModule(RepositoryModule())
            .useCasesModule(UseCasesModule())
            .remoteUseCasesModule(RemoteUseCasesModule())
            .build()
            .inject(this)
    }



}