package net.sakuragame.eternal.kirramodel.compat.skript

import ch.njol.skript.Skript
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import net.sakuragame.eternal.kirramodel.compat.skript.event.ModelInteractEvent
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
        Skript.registerEvent("model interact", ModelInteractEvent::class.java, net.sakuragame.eternal.kirramodel.event.ModelInteractEvent::class.java)
        EventValues.registerEventValue(net.sakuragame.eternal.kirramodel.event.ModelInteractEvent::class.java, Player::class.java,
            object : Getter<Player, net.sakuragame.eternal.kirramodel.event.ModelInteractEvent>() {

                override fun get(arg: net.sakuragame.eternal.kirramodel.event.ModelInteractEvent): Player {
                    return arg.player
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