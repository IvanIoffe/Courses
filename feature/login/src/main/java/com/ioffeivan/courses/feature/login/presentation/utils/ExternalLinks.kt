package com.ioffeivan.courses.feature.login.presentation.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

object ExternalLinks {
    const val VK = "https://vk.com/"
    const val OK = "https://ok.ru/"
}

fun Context.openUrl(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, url.toUri())
    startActivity(browserIntent)
}