package net.sakuragame.eternal.kirramodel.function

import ink.ptms.adyeshach.api.event.AdyeshachEntityInteractEvent
import net.sakuragame.eternal.kirramodel.KirraModelAPI
import net.sakuragame.eternal.kirramodel.model.meta.sub.InteractType
import org.bukkit.event.player.PlayerMoveEvent
import taboolib.common.platform.event.SubscribeEvent

object FunctionListener {

    @SubscribeEvent
    fun e(e: AdyeshachEntityInteractEvent) {
        if (!e.isMainHand) {
            return
        }
        val player = e.player
        val entity = e.entity
        val model = KirraModelAPI.getModelByEntityUUID(entity.normalizeUniqueId) ?: return
        if (model.interactMeta.type == InteractType.CLICK) {
            model.interactMeta.interact(player = player, type = InteractType.CLICK)
        }
    }

    @SubscribeEvent
    fun e(e: PlayerMoveEvent) {

    }
}