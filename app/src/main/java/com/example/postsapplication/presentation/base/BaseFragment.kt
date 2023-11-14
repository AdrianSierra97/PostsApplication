package com.example.postsapplication.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupView()
    }

    abstract fun setupView()

    protected open fun setupObservers() {
        //Setup observers in child fragments
    }

    fun navigateWith(view: View, action: NavController.() -> Unit) {
        val navController = findNavController(view)
        action(navController)
    }

    protected inline fun <reified VM : ViewModel> provideViewModel(): VM =
        ViewModelProvider(this)[VM::class.java]

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
