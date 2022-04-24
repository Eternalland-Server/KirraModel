package net.sakuragame.eternal.kirramodel.model.meta.sub

import ink.ptms.adyeshach.common.entity.EntityInstance
import net.sakuragame.eternal.dragoncore.network.PacketSender
import net.sakuragame.eternal.kirramodel.KirraModelAPI
import net.sakuragame.eternal.kirramodel.getNearByPlayers
import taboolib.common.platform.function.submit
import taboolib.common.platform.service.PlatformExecutor

data class Timer(val enabled: Boolean, val delay: Long, val period: Long, val animation: String) {

    fun play(entity: EntityInstance) {
        if (!enabled) {
            return
        }
        val uuid = entity.normalizeUniqueId
        tasks += submit(async = true, delay = delay, period = period) {
            val loc = entity.getLocation()
            val players = loc.getNearByPlayers(KirraModelAPI.viewDistance)
            if (players.isEmpty()) {
                return@submit
            }
            PacketSender.setModelEntityAnimation(players, uuid, animation, 20)
        }
    }

    companion object {

        private val tasks = mutableListOf<PlatformExecutor.PlatformTask>()

        fun recycle() {
            tasks.forEach {
                it.cancel()
            }
            tasks.clear()
        }
    }
}