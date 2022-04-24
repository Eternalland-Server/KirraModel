package net.sakuragame.eternal.kirramodel.model

import ink.ptms.adyeshach.api.AdyeshachAPI
import ink.ptms.adyeshach.common.entity.EntityInstance
import ink.ptms.adyeshach.common.entity.EntityTypes
import org.bukkit.Location

data class KEntity(val entity: EntityInstance) {

    // 销毁内部发包实体.
    fun destroy() {
        entity.remove()
    }

    companion object {

        fun createKEntity(name: String, loc: Location): KEntity {
            val entity = AdyeshachAPI.getEntityManagerPublicTemporary().create(EntityTypes.ARMOR_STAND, loc).apply {
                setCustomName(name)
                setNoGravity(true)
            }
            return KEntity(entity = entity)
        }
    }
}