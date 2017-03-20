package com.keepnotes.utils;


import android.text.TextUtils;

import java.util.regex.Pattern;

public class ValidationUtils {

    private static final Pattern VALID_TITLE_SYMBOLS = Pattern.compile("[a-zA-Z0-9 ]+");

    public static boolean isValidTitleLength(String title) {
        return !TextUtils.isEmpty(title) && title.length() > 4 && title.length() < 10;
    }

    public static boolean isValidTitleSymbols(String title) {
        return !TextUtils.isEmpty(title) && VALID_TITLE_SYMBOLS.matcher(title).matches();
    }

    public static boolean isValidDescriptionLength(String description) {
        return !TextUtils.isEmpty(description) && description.length() > 0 && description.length() < 501;
    }
}
