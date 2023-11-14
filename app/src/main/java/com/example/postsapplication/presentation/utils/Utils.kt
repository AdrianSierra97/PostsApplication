package com.example.postsapplication.presentation.utils

inline fun <reified T> mySafeCast(obj: Any): T? {
    return obj as? T
}