package com.myth.plugin.modular

import org.gradle.api.Project
import org.gradle.api.initialization.Settings

inline fun <reified T> Settings.getProperty(name: String): T {
    return this.extensions.extraProperties.get(name) as T
}

inline fun <reified T> Project.getProperty(name: String): T {
    return this.properties.getValue(name) as T
}