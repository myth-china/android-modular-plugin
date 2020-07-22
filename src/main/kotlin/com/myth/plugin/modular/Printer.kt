package com.myth.plugin.modular

object Printer {

    fun println(text: String) {
        kotlin.io.println("android-modular-plugin >>>> $text")
    }
}