package net.sakuragame.eternal.kirramodel

import net.sakuragame.eternal.kirramodel.model.Model
import net.sakuragame.eternal.kirramodel.model.meta.AnimationMeta
import net.sakuragame.eternal.kirramodel.model.meta.sub.Timer
import org.bukkit.Location
import taboolib.common5.Baffle
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToLong

@Suppress("SpellCheckingInspection")
object KirraModelAPI {

    val viewDistance: Double
        get() = KirraModel.conf.getDouble("view-distance")

    val walkDistance: Double
        get() = KirraModel.conf.getDouble("walk-distance")

    val interactBaffle by lazy {
        Baffle.of((KirraModel.conf.getDouble("interact-cooldown-secs") * 1000).roundToLong(), TimeUnit.MILLISECONDS)
    }

    val models = mutableMapOf<String, Model>()

    fun recycleModels() {
        Timer.recycle()
        AnimationMeta.recycle()
        models.forEach {
            it.value.kEntity?.destroy()
        }
        models.clear()
    }

    fun removeModel(id: String): Boolean {
        val model = models[id] ?: return false
        model.kEntity?.entity?.remove() ?: return false
        return true
    }

    fun createTempModel(loc: Location, model: Model, id: String? = null) {
        val mapId = id ?: ("TEMP_" + model.id + UUID.randomUUID().toString())
        models += mapId to Model(id = model.id, name = model.name, temp = true, loc = loc, interactMeta = model.interactMeta, animationMeta = model.animationMeta)
    }

    fun getModelByEntityUUID(uuid: UUID) = models.values.find { it.kEntity?.entity?.normalizeUniqueId == uuid }
}