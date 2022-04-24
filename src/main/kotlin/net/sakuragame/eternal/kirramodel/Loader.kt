package net.sakuragame.eternal.kirramodel

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.submit
import taboolib.module.configuration.ConfigFile
import taboolib.module.configuration.Configuration
import taboolib.module.configuration.Type
import java.io.File

object Loader {

    private val folder by lazy {
        File(KirraModel.plugin.dataFolder, "models")
    }

    @Awake(LifeCycle.ENABLE)
    fun i() {
        reload()
    }

    fun reload() {
        submit(async = true) {
            KirraModelAPI.recycleModels()
            val yamlFiles = folder.listFiles()!!.map { Configuration.loadFromFile(it, Type.YAML) }
            yamlFiles.forEach {
                load(it)
            }
        }
    }

    private fun load(file: ConfigFile) {
        file.getKeys(false).forEach { id ->
            val displayName = file.getString("$id.display-name") ?: nullError("displayName")

        }
    }
}