package com.example.demoproject.utility

import android.content.Context

object Util {
    fun Int.dpToPx(context: Context): Int =
        (this * context.resources.displayMetrics.density).toInt()

}