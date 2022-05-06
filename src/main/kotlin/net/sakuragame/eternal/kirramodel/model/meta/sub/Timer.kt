package net.sakuragame.eternal.kirramodel.model.meta.sub

import ink.ptms.adyeshach.common.entity.EntityInstance
import net.sakuragame.eternal.kirramodel.KirraModelAPI
import net.sakuragame.eternal.kirramodel.getNearByPlayers
import net.sakuragame.eternal.kirramodel.model.Recyclable
import org.bukkit.Bukkit
import taboolib.common.platform.function.submit
import taboolib.common.platform.service.PlatformExecutor

data class Timer(val enabled: Boolean, val delay: Long, val period: Long, val animation: Animation?) {

    fun start(entity: EntityInstance) {
        if (!enabled || animation == null) {
            return
        }
        val uuid = entity.normalizeUniqueId
        tasks += submit(async = true, delay = delay, period = period) {
            val loc = entity.getLocation()
            val players = loc.getNearByPlayers(KirraModelAPI.viewDistance)
            if (players.isEmpty()) {
                return@submit
            }
            animation.play(uuid, players)
        }
    }

    companion object : Recyclable {

        private val tasks = mutableListOf<PlatformExecutor.PlatformTask>()

        override fun recycle() {
            tasks.forEach {
                it.cancel()
            }
            tasks.clear()
        }
    }
}