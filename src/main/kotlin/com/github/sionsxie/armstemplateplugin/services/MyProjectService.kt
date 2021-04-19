package com.github.sionsxie.armstemplateplugin.services

import com.github.sionsxie.armstemplateplugin.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
