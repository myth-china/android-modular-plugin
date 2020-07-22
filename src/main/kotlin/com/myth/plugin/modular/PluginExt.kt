package com.myth.plugin.modular

import org.gradle.api.Project
import org.gradle.api.initialization.Settings

inline fun <reified T> Settings.getByName(name: String): T {
    return this.extensions.getByName(name) as T
}

inline fun <reified T> Project.getByName(name: String): T {
    return this.extensions.getByName(name) as T
}