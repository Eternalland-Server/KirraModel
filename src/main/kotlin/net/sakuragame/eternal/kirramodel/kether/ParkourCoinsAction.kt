package net.sakuragame.eternal.kirramodel.kether

import net.sakuragame.eternal.kirramodel.nullError
import net.sakuragame.kirracore.bukkit.profile.ProfileManager
import org.bukkit.Bukkit
import taboolib.common5.Coerce
import taboolib.module.kether.Kether
import taboolib.module.kether.PlayerOperator

object ParkourCoinsAction {

    init {
        Kether.registeredPlayerOperator["PARKOUR_COINS"] = PlayerOperator(
            reader = PlayerOperator.Reader {
                val bukkitPlayer = Bukkit.getPlayer(it.uniqueId) ?: nullError("Player")
                val profile = ProfileManager.getProfile(bukkitPlayer) ?: nullError("Profile")
                profile.parkourCoins
            },
            writer = PlayerOperator.Writer { player, method, value ->
                val bukkitPlayer = Bukkit.getPlayer(player.uniqueId) ?: nullError("Player")
                val profile = ProfileManager.getProfile(bukkitPlayer) ?: nullError("Profile")
                val intValue = Coerce.asInteger(value).get()
                when (method) {
                    PlayerOperator.Method.INCREASE -> profile.setParkourCoins(profile.getParkourCoins() + intValue)
                    PlayerOperator.Method.DECREASE -> profile.setParkourCoins(profile.getParkourCoins() - intValue)
                    PlayerOperator.Method.MODIFY -> profile.setParkourCoins(intValue)
                    PlayerOperator.Method.NONE -> error("Not supported.")
                }
            },
            usable = PlayerOperator.Method.values()
        )
    }
}