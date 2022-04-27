package net.sakuragame.eternal.kirramodel

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.releaseResourceFile
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import taboolib.platform.BukkitPlugin

@Suppress("SpellCheckingInspection")
object KirraModel : Plugin() {

    @Config
    lateinit var conf: Configuration
        private set

    val plugin by lazy {
        BukkitPlugin.getInstance()
    }

    override fun onEnable() {
        releaseResourceFile("models")
    }
}