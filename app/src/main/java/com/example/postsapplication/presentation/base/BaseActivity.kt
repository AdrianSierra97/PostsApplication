package com.example.postsapplication.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)
        setupToolbar()
    }

    abstract fun getViewBinding(): VB

    abstract fun setupToolbar()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
