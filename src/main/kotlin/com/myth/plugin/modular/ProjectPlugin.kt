package com.myth.plugin.modular

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.artifacts.DefaultModuleIdentifier
import org.gradle.internal.component.external.model.DefaultModuleComponentSelector
import org.gradle.internal.component.local.model.DefaultProjectComponentSelector

class ProjectPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        Printer.println("start project process")

        val globalSrcDebug = project.getByName<Boolean?>("globalSrcDebug")

        project.configurations.all { configuration ->
            configuration.resolutionStrategy {
                it.dependencySubstitution { ds ->
                    project.getByName<List<Module>>("modules").forEach { m ->
                        if (globalSrcDebug == true || m.srcDebug == true) {

                            val realName = m.name ?: throw getIllegalArgumentException(m, "name error")
                            val realGroup = m.group ?: throw getIllegalArgumentException(m, "group error")
                            val realArtifactId =
                                m.artifactId ?: throw getIllegalArgumentException(m, "artifactId error")

                            ds.substitute(
                                DefaultModuleComponentSelector.newSelector(
                                    DefaultModuleIdentifier.newId(
                                        realGroup,
                                        realArtifactId
                                    ), ""
                                )
                            ).with(
                                DefaultProjectComponentSelector.newSelector(
                                    project.project(":$realName")
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}