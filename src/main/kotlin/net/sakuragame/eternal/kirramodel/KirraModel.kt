package net.sakuragame.eternal.kirramodel

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.platform.BukkitPlugin

@Suppress("SpellCheckingInspection")
object KirraModel : Plugin() {

    val plugin by lazy {
        BukkitPlugin.getInstance()
    }
}