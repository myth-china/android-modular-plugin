package com.myth.plugin.modular

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import java.io.File

class SettingsPlugin : Plugin<Settings> {

    override fun apply(settings: Settings) {
        Printer.println("start settings process")

        val globalSrcDebug = settings.getByName<Boolean?>("globalSrcDebug")

        settings.getByName<List<Module>>("modules").forEach {
            if (globalSrcDebug == true || it.srcDebug == true) {
                val realName = it.name ?: throw getIllegalArgumentException(it, "module name error, must not be null!")
                val realPath = it.path ?: throw getIllegalArgumentException(it, "module path error ${it.path}")

                println("--------------------------------")
                println("src debug :${realName}")

                val projectName = ":${realName}"
                settings.include(projectName)
                settings.project(projectName).projectDir = File(realPath)
            }
        }
    }
}