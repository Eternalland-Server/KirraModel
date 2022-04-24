package net.sakuragame.eternal.kirramodel.model.meta

import net.sakuragame.eternal.kirramodel.KirraModelAPI
import net.sakuragame.eternal.kirramodel.model.meta.sub.InteractType
import org.bukkit.entity.Player

data class InteractMeta(val enabled: Boolean, val type: InteractType, val animation: String, val actions: List<String>, val destroy: Boolean) {

    fun interact(player: Player, type: InteractType) {
        if (!KirraModelAPI.interactBaffle.hasNext(player.name)) {
            return
        }

        KirraModelAPI.interactBaffle.next(player.name)
    }
}