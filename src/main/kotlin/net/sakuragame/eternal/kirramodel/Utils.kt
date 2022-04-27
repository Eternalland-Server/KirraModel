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

fun Location.parseToString(): String {
    return "${world.name} @ $x @ $y @ $z @ $yaw @ $pitch"
}

fun String.parseToLoc(): Location? {
    val split = split(" @ ")
    if (split.size != 6) {
        return null
    }
    val world = Bukkit.getWorld(split[0]) ?: return null
    val x = split[1].toDoubleOrNull() ?: return null
    val y = split[2].toDoubleOrNull() ?: return null
    val z = split[3].toDoubleOrNull() ?: return null
    val yaw = split[4].toFloatOrNull() ?: return null
    val pitch = split[5].toFloatOrNull() ?: return null
    return Location(world, x, y, z, yaw, pitch)
}