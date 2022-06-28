package net.sakuragame.eternal.kirramodel.compat.skript

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import net.sakuragame.eternal.kirramodel.event.ModelInteractEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.info

@Suppress("SpellCheckingInspection")
object CompatSkript {

    @Awake(LifeCycle.ENABLE)
    fun i() {
        if (Bukkit.getPluginManager().getPlugin("Skript") == null) {
            return
        }
        info("与 Skript 进行挂钩.")
        registerEvents()
        registerEffects()
    }

    private fun registerEffects() {
    }

    private fun registerEvents() {
        Skript.registerEvent("model interact", SimpleEvent::class.java, ModelInteractEvent::class.java, "model interact")
        EventValues.registerEventValue(
            ModelInteractEvent::class.java, Player::class.java,
            object : Getter<Player, ModelInteractEvent>() {

                override fun get(arg: ModelInteractEvent): Player {
                    return arg.player
                }
            }, 0
        )
        EventValues.registerEventValue(
            ModelInteractEvent::class.java, Boolean::class.java,
            object : Getter<Boolean, ModelInteractEvent>() {

                override fun get(arg: ModelInteractEvent): Boolean {
                    return arg.model.interactMeta.destroy
                }
            }, 0
        )
        EventValues.registerEventValue(
            ModelInteractEvent::class.java, String::class.java,
            object : Getter<String, ModelInteractEvent>() {

                override fun get(arg: ModelInteractEvent): String {
                    return arg.model.id
                }
            }, 0
        )
    }
}