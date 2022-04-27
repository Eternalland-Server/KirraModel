package net.sakuragame.eternal.kirramodel.model.meta

import ink.ptms.adyeshach.common.entity.EntityInstance
import net.sakuragame.eternal.kirramodel.KirraModelAPI
import net.sakuragame.eternal.kirramodel.model.meta.sub.Animation
import net.sakuragame.eternal.kirramodel.model.meta.sub.InteractType
import org.bukkit.entity.Player
import taboolib.common.platform.function.adaptCommandSender
import taboolib.common.platform.function.adaptPlayer
import taboolib.common.platform.function.warning
import taboolib.module.kether.KetherShell
import java.util.*
import java.util.concurrent.CompletableFuture

@Suppress("SpellCheckingInspection")
data class InteractMeta(val enabled: Boolean, val type: InteractType, val animation: Animation, val actions: List<String>, val destroy: Boolean) {

    lateinit var entityUUID: UUID

    fun init(entity: EntityInstance) {
        entityUUID = entity.normalizeUniqueId
    }

    fun interact(player: Player) {
        if (!KirraModelAPI.interactBaffle.hasNext(player.name)) {
            return
        }
        if (!enabled) {
            return
        }
        try {
            KetherShell.eval(actions, namespace = listOf("kirramodel")) {
                this.sender = adaptPlayer(player)
            }
        } catch (e: Throwable) {
            warning("Kether 在执行脚本时遇到了一个错误.")
            warning(e)
            CompletableFuture.completedFuture(false)
        }
        if (destroy) {
            KirraModelAPI.getModelByEntityUUID(entityUUID)?.let { it.kEntity?.destroy() }
        }
        KirraModelAPI.interactBaffle.next(player.name)
    }
}