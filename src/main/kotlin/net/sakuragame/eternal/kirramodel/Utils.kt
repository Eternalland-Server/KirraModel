package net.sakuragame.eternal.kirramodel

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

fun nullError(fieldName: String): Nothing = error("$fieldName can not be null.")

fun Location.getNearByPlayers(radius: Double): List<Player> {
    return Bukkit.getOnlinePlayers()
        .filter { safeDistance(it.location) <= radius }
}

fun Location.safeDistance(loc: Location): Double {
    return if (this.world!!.name == loc.world!!.name) {
        distance(loc)
    } else {
        Double.MAX_VALUE
    }
}