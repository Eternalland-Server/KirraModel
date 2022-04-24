package net.sakuragame.eternal.kirramodel

import net.sakuragame.eternal.kirramodel.model.Model
import net.sakuragame.eternal.kirramodel.model.meta.sub.Timer

@Suppress("SpellCheckingInspection")
object KirraModelAPI {

    val viewDistance: Double
        get() = KirraModel.conf.getDouble("view-distance")

    val models = mutableListOf<Model>()

    fun recycleModels() {
        Timer.recycle()
        models.forEach {
            it.kEntity.destroy()
        }
        models.clear()
    }
}