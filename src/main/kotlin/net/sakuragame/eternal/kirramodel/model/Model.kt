package net.sakuragame.eternal.kirramodel.model

import net.sakuragame.eternal.kirramodel.model.meta.AnimationMeta
import net.sakuragame.eternal.kirramodel.model.meta.InteractMeta
import org.bukkit.Location

@Suppress("MemberVisibilityCanBePrivate")
class Model(val id: String, val name: String, loc: Location?, interactMeta: InteractMeta, animationMeta: AnimationMeta) {

    var kEntity: KEntity? = null

    val interactMeta: InteractMeta

    val animationMeta: AnimationMeta

    init {
        this.interactMeta = interactMeta
        this.animationMeta = animationMeta
        if (loc != null) {
            val tempKEntity = KEntity.createKEntity(name, loc)
            this.kEntity = tempKEntity
            interactMeta.init(tempKEntity.entity)
            animationMeta.init(tempKEntity.entity)
        }
    }
}