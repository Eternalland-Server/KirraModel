package net.sakuragame.eternal.kirramodel.model.meta.sub

import net.sakuragame.eternal.dragoncore.network.PacketSender
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

data class Animation(val name: String, val transientTime: Int) {

    fun play(entityUUID: UUID) {
        PacketSender.setModelEntityAnimation(Bukkit.getOnlinePlayers().toList(), entityUUID, name, transientTime)
    }

    fun play(entityUUID: UUID, players: List<Player>) {
        PacketSender.setModelEntityAnimation(players, entityUUID, name, transientTime)
    }
}
