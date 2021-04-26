package com.example.roomdatamvvm.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

object Clicks {
    fun <T> callIntent(context: Context, ofClass: Class<T>) {
        val intent = Intent(context, ofClass)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(context, intent, null)
    }
}