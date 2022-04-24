package net.sakuragame.eternal.kirramodel.model

import net.sakuragame.eternal.kirramodel.model.meta.AnimationMeta
import net.sakuragame.eternal.kirramodel.model.meta.InteractMeta
import org.bukkit.Location

class Model(name: String, loc: Location, interactMeta: InteractMeta, animationMeta: AnimationMeta) {

    val kEntity: KEntity

    val interactMeta: InteractMeta

    val animationMeta: AnimationMeta

    init {
        this.kEntity = KEntity.createKEntity(name, loc)
        this.interactMeta = interactMeta
        this.animationMeta = animationMeta

    }
}