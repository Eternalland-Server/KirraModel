package net.sakuragame.eternal.kirramodel.function

import ink.ptms.adyeshach.api.AdyeshachAPI
import ink.ptms.adyeshach.api.event.AdyeshachEntityDamageEvent
import ink.ptms.adyeshach.api.event.AdyeshachEntityInteractEvent
import ink.ptms.adyeshach.common.entity.EntityInstance
import net.sakuragame.eternal.kirramodel.KirraModelAPI
import net.sakuragame.eternal.kirramodel.model.meta.sub.InteractType
import net.sakuragame.eternal.kirramodel.safeDistance
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerToggleSprintEvent
import taboolib.common.platform.event.SubscribeEvent

object FunctionListener {

    @SubscribeEvent
    fun e(e: AdyeshachEntityDamageEvent) {
        val player = e.player
        val entity = e.entity
        val model = KirraModelAPI.getModelByEntityUUID(entity.normalizeUniqueId) ?: return
        if (model.interactMeta.type == InteractType.LEFT_CLICK) {
            model.interactMeta.interact(player = player)
        }
    }

    @SubscribeEvent
    fun e(e: AdyeshachEntityInteractEvent) {
        if (!e.isMainHand) {
            return
        }
        val player = e.player
        val entity = e.entity
        val model = KirraModelAPI.getModelByEntityUUID(entity.normalizeUniqueId) ?: return
        if (model.interactMeta.type == InteractType.RIGHT_CLICK) {
            model.interactMeta.interact(player = player)
        }
    }

    @SubscribeEvent
    fun e(e: PlayerToggleSprintEvent) {}

    @SubscribeEvent
    fun e(e: PlayerMoveEvent) {
        val player = e.player
        val entity = player.getEntityNearly() ?: return
        val model = KirraModelAPI.getModelByEntityUUID(entity.normalizeUniqueId) ?: return
        if (model.interactMeta.type == InteractType.WALK) {
            model.interactMeta.interact(player = player)
        }
    }

    private fun Player.getEntityNearly(): EntityInstance? {
        return AdyeshachAPI.getEntity(this) { entity -> entity.getLocation().safeDistance(location) <= KirraModelAPI.walkDistance }
    }
}