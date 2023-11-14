package com.example.postsapplication.presentation

import com.example.postsapplication.databinding.ActivityMainBinding
import com.example.postsapplication.presentation.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    override fun setupToolbar() {
        supportActionBar?.hide()
    }

}