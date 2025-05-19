package com.ioffeivan.courses.feature.login.presentation.utils

import android.text.InputFilter
import android.text.Spanned

object CyrillicInputFilter : InputFilter {
    private val regex = Regex("[а-яА-ЯёЁ]")

    override fun filter(
        source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int
    ): CharSequence? {
        return if (source?.contains(regex) == true) {
            source.replace(regex, "")
        } else {
            source
        }
    }
}