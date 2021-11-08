package com.starwars.starwarsviewer.util.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.show(show: Boolean) {
    visibility = if (show) VISIBLE else GONE
}