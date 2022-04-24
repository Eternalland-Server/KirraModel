package net.sakuragame.eternal.kirramodel.model.meta

import net.sakuragame.eternal.kirramodel.model.meta.sub.InteractType

data class InteractMeta(val enabled: Boolean, val type: InteractType, val animation: String, val actions: List<String>, val destroy: Boolean) {
}