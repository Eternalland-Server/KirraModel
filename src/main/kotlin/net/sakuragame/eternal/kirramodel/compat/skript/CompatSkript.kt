package net.sakuragame.eternal.kirramodel.compat.skript

import ch.njol.skript.Skript
import ch.njol.skript.classes.ClassInfo
import ch.njol.skript.classes.Parser
import ch.njol.skript.expressions.base.EventValueExpression
import ch.njol.skript.lang.ParseContext
import ch.njol.skript.lang.util.SimpleEvent
import ch.njol.skript.registrations.Classes
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import net.sakuragame.eternal.kirramodel.compat.skript.effect.EffectDestroyModel
import net.sakuragame.eternal.kirramodel.event.ModelInteractEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.info
import java.util.*
import javax.annotation.Nullable


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
        Skript.registerEffect(EffectDestroyModel::class.java, "destroy model by uuid %uuid%")
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
    }

//    private fun registerExpressions() {
//        Skript.registerExpression()
//    }
}