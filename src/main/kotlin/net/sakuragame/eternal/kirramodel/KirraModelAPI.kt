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

    fun createTempModel(loc: Location, model: Model) {

    }

    fun getModelByEntityUUID(uuid: UUID) = models.values.find { it.kEntity?.entity?.normalizeUniqueId == uuid }
}