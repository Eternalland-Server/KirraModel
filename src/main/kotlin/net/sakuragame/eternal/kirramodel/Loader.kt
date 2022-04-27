package net.sakuragame.eternal.kirramodel

import net.sakuragame.eternal.kirramodel.model.Model
import net.sakuragame.eternal.kirramodel.model.meta.AnimationMeta
import net.sakuragame.eternal.kirramodel.model.meta.InteractMeta
import net.sakuragame.eternal.kirramodel.model.meta.sub.Animation
import net.sakuragame.eternal.kirramodel.model.meta.sub.InteractType
import net.sakuragame.eternal.kirramodel.model.meta.sub.Timer
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.submit
import taboolib.module.configuration.ConfigFile
import taboolib.module.configuration.Configuration
import taboolib.module.configuration.Type
import taboolib.module.configuration.util.getStringColored
import java.io.File

@Suppress("MemberVisibilityCanBePrivate")
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
            val yamlFiles = folder.listFiles()?.map { Configuration.loadFromFile(it, Type.YAML) } ?: return@submit
            yamlFiles.forEach {
                load(it)
            }
        }
    }

    private fun load(file: ConfigFile) {
        file.getKeys(false).forEach { id ->
            val displayName = file.getStringColored("$id.display-name") ?: nullError("displayName")
            val loc = file.getBukkitLocation("$id.loc")
            val interactMeta = file.getInteractMeta("$id.interact")
            val animationMeta = file.getAnimationMeta("$id.animation")
            KirraModelAPI.models[id] = Model(displayName, loc, interactMeta, animationMeta)
        }
    }

    private fun Configuration.getBukkitLocation(path: String) = getString(path)?.parseToLoc()

    private fun Configuration.getAnimation(path: String): Animation? {
        val split = getString(path)?.split(" @ ") ?: return null
        return Animation(split[0], split[1].toInt())
    }

    private fun Configuration.getInteractMeta(path: String): InteractMeta {
        val enable = getBoolean("$path.enable")
        val type = InteractType.values().find { it.name == getString("$path.type")?.uppercase() } ?: nullError("type")
        val animation = getAnimation("$path.animation") ?: nullError("animation")
        val actions = getStringList("$path.actions")
        val destroy = getBoolean("$path.destroy")
        return InteractMeta(enable, type, animation, actions, destroy)
    }

    private fun Configuration.getAnimationMeta(path: String): AnimationMeta {
        val idleAnimation = getAnimation("$path.idle") ?: nullError("idleAnimation")
        val timer = getTimer("$path.timer")
        return AnimationMeta(idleAnimation, timer)
    }

    private fun Configuration.getTimer(path: String): Timer {
        val enable = getBoolean("$path.enable")
        val delay = getLong("$path.delay")
        val period = getLong("$path.period")
        val animation = getAnimation("$path.value") ?: nullError("timerAnimation")
        return Timer(enable, delay, period, animation)
    }
}