package com.keepnotes.enums

enum class Color(val colorName: String, val hex: Double = 1.0) {

    RED("Red"),
    GREEN("Green", 0.5),
    BLUE("Blue")
}