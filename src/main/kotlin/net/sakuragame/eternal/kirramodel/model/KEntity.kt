package net.sakuragame.eternal.kirramodel.model

import ink.ptms.adyeshach.common.entity.EntityInstance

data class KEntity(val entity: EntityInstance) {

    // 销毁内部发包实体.
    fun destroy() {
        entity.remove()
    }
}