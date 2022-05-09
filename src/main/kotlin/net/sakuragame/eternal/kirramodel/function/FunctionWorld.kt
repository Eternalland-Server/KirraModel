package net.sakuragame.eternal.kirramodel.function

import ink.ptms.adyeshach.api.AdyeshachAPI
import org.bukkit.event.world.WorldUnloadEvent
import taboolib.common.platform.event.SubscribeEvent

object FunctionWorld {

    @SubscribeEvent
    fun e(e: WorldUnloadEvent) {
        AdyeshachAPI.getEntityManagerPublicTemporary().getEntities().forEach {
            if (it.getWorld().uid == e.world.uid) {
                it.remove()
            }
        }
    }
}