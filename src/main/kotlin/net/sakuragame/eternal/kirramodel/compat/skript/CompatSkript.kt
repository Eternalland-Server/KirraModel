package net.sakuragame.eternal.kirramodel.compat.skript

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import net.sakuragame.eternal.kirramodel.compat.skript.effect.ActionTextEffect
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
        Skript.registerEffect(ActionTextEffect::class.java, "send eternal action tip %string% to %player%")
    }

    private fun registerEvents() {
        Skript.registerEvent("model interact", SimpleEvent::class.java, net.sakuragame.eternal.kirramodel.event.ModelInteractEvent::class.java, "model interact")
        EventValues.registerEventValue(net.sakuragame.eternal.kirramodel.event.ModelInteractEvent::class.java, Player::class.java,
            object : Getter<Player, net.sakuragame.eternal.kirramodel.event.ModelInteractEvent>() {

                override fun get(arg: net.sakuragame.eternal.kirramodel.event.ModelInteractEvent): Player {
                    return arg.player
                }
            }, 0)
        EventValues.registerEventValue(net.sakuragame.eternal.kirramodel.event.ModelInteractEvent::class.java, Boolean::class.java,
            object : Getter<Boolean, net.sakuragame.eternal.kirramodel.event.ModelInteractEvent>() {

                override fun get(arg: net.sakuragame.eternal.kirramodel.event.ModelInteractEvent): Boolean {
                    return arg.model.interactMeta.destroy
                }
            }, 0)
        EventValues.registerEventValue(net.sakuragame.eternal.kirramodel.event.ModelInteractEvent::class.java, String::class.java,
            object : Getter<String, net.sakuragame.eternal.kirramodel.event.ModelInteractEvent>() {

                override fun get(arg: net.sakuragame.eternal.kirramodel.event.ModelInteractEvent): String {
                    return arg.model.id
                }
            }, 0)
    }
}