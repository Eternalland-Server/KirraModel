package net.sakuragame.eternal.kirramodel.event

import net.sakuragame.eternal.kirramodel.model.Model
import org.bukkit.entity.Player
import taboolib.platform.type.BukkitProxyEvent

class ModelInteractEvent(val player: Player, val model: Model) : BukkitProxyEvent()