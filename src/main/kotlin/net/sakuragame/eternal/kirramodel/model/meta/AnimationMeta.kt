package net.sakuragame.eternal.kirramodel.model.meta

import ink.ptms.adyeshach.common.entity.EntityInstance
import net.sakuragame.eternal.kirramodel.model.meta.sub.Animation
import net.sakuragame.eternal.kirramodel.model.meta.sub.Timer

data class AnimationMeta(val idle: Animation, val timer: Timer) {

    fun init(entity: EntityInstance) {
    }
}