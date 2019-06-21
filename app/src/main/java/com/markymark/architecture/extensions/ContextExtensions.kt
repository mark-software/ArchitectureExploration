package com.markymark.architecture.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes

inline fun <reified T : Activity> Context.startActivity() {
    startActivity(Intent(applicationContext, T::class.java))
}

fun Context.showToast(@StringRes resId: Int, duration: Int) {
    Toast.makeText(this, resId, duration).show()
}

fun Context.showShortToast(@StringRes resId: Int) {
    showToast(resId, Toast.LENGTH_SHORT)
}

fun Context.showShortToast(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
