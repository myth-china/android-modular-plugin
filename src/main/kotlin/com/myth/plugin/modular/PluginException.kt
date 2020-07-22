package com.myth.plugin.modular

fun getIllegalArgumentException(module: Module, text: String): IllegalArgumentException {
    return IllegalArgumentException("android-modular-plugin >>>> name:${module.name} exception:$text")
}