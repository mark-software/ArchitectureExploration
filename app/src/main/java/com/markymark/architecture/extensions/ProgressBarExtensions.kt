package com.markymark.architecture.extensions

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.invisible() {
    visibility = View.INVISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}