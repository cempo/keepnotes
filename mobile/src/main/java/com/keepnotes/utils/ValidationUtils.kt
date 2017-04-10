package com.keepnotes.utils


import android.text.TextUtils

import java.util.regex.Pattern

object ValidationUtils {
    private val VALID_TITLE_SYMBOLS = Pattern.compile("[a-zA-Z0-9 ]+")

    fun isValidTitleLength(title: String): Boolean {
        return !TextUtils.isEmpty(title) && title.length > 4 && title.length < 10
    }

    fun isValidTitleSymbols(title: String): Boolean {
        return !TextUtils.isEmpty(title) && VALID_TITLE_SYMBOLS.matcher(title).matches()
    }

    fun isValidDescriptionLength(description: String): Boolean {
        return !TextUtils.isEmpty(description) && description.length < 501
    }
}
