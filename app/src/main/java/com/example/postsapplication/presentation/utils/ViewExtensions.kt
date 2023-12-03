package com.example.postsapplication.presentation.utils

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment


fun EditText.isNotEmpty() = this.text.toString().isNotEmpty()

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.requireContext(), message, duration).show()
}

