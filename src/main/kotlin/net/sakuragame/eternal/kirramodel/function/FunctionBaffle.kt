package net.sakuragame.eternal.kirramodel.function

import net.sakuragame.eternal.kirramodel.KirraModelAPI
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.event.SubscribeEvent

object FunctionBaffle {

    @SubscribeEvent
    fun e(e: PlayerQuitEvent) {
        KirraModelAPI.interactBaffle.reset(e.player.name)
    }
}