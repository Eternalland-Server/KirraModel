package net.sakuragame.eternal.kirramodel.model.meta

import ink.ptms.adyeshach.api.event.AdyeshachEntityVisibleEvent
import ink.ptms.adyeshach.common.entity.EntityInstance
import net.sakuragame.eternal.kirramodel.model.Recyclable
import net.sakuragame.eternal.kirramodel.model.meta.sub.Animation
import net.sakuragame.eternal.kirramodel.model.meta.sub.Timer
import taboolib.common.platform.event.SubscribeEvent
import java.util.*

data class AnimationMeta(val idle: Animation, val timer: Timer) {

    lateinit var entityUUID: UUID

    fun init(entity: EntityInstance) {
        entityUUID = entity.normalizeUniqueId
        animationMap[entityUUID] = idle
        timer.start(entity)
    }

    companion object : Recyclable {

        val animationMap = mutableMapOf<UUID, Animation>()

        @SubscribeEvent
        fun e(e: AdyeshachEntityVisibleEvent) {
            val entity = e.entity
            val uuid = entity.normalizeUniqueId
            val animation = animationMap[uuid] ?: return
            val viewer = e.viewer
            animation.play(uuid, listOf(viewer))
        }

        override fun recycle() {
            animationMap.clear()
        }
    }
}