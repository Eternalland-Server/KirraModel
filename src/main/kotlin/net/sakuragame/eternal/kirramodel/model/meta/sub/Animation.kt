package net.sakuragame.eternal.kirramodel.model.meta.sub

import net.sakuragame.eternal.dragoncore.network.PacketSender
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.function.submit
import java.util.*

data class Animation(val name: String, val transientTime: Int) {

    fun play(entityUUID: UUID) {
        submit(async = true, delay = 20L) {
            PacketSender.setModelEntityAnimation(Bukkit.getOnlinePlayers().toList(), entityUUID, name, transientTime)
        }
    }

    fun play(entityUUID: UUID, players: List<Player>) {
        submit(async = true, delay = 20L) {
            PacketSender.setModelEntityAnimation(players, entityUUID, name, transientTime)
        }
    }
}
