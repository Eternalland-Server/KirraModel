package net.sakuragame.eternal.kirramodel.model

import ink.ptms.adyeshach.api.AdyeshachAPI
import ink.ptms.adyeshach.common.entity.EntityInstance
import ink.ptms.adyeshach.common.entity.EntityTypes
import org.bukkit.Location
import taboolib.module.chat.colored

data class KEntity(val entity: EntityInstance) {

    fun destroy() {
        entity.clearViewer()
        AdyeshachAPI.getEntityManagerPublicTemporary().removeEntity(entity)
    }

    companion object {

        fun createKEntity(name: String, loc: Location): KEntity {
            val entity = AdyeshachAPI.getEntityManagerPublicTemporary().create(EntityTypes.ARMOR_STAND, loc).apply {
                setCustomName(name.colored())
                setCustomNameVisible(false)
                setNoGravity(true)
            }
            return KEntity(entity = entity)
        }
    }
}