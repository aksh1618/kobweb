@file:Suppress("LeakingThis") // Following official Gradle guidance

package com.varabyte.kobweb.gradle.application.tasks

import com.varabyte.kobweb.gradle.application.extensions.KobwebBlock
import com.varabyte.kobweb.gradle.application.project.api.ApiData
import com.varabyte.kobweb.gradle.application.templates.createApisFactoryImpl
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File
import javax.inject.Inject

abstract class KobwebGenerateApiTask @Inject constructor(kobwebBlock: KobwebBlock) :
    KobwebProjectTask(kobwebBlock, "Generate Kobweb code for the server") {

    @InputFiles
    fun getSourceFiles() = getSourceFilesJvm()

    @OutputDirectory
    fun getGenDir(): File = kobwebBlock.getGenJvmSrcRoot(project)

    @TaskAction
    fun execute() {
        val getSrcRoot = getGenDir()

        with(
            ApiData.from(
                project.group.toString(),
                kobwebBlock.apiPackage.get(),
                getSourceFilesJvm(),
                GradleReporter(project.logger)
            )
        ) {
            getSrcRoot.mkdirs()
            File(getSrcRoot, "ApisFactoryImpl.kt").writeText(createApisFactoryImpl(this))
        }
    }
}