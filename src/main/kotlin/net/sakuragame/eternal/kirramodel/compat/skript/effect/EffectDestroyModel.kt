package net.sakuragame.eternal.kirramodel.compat.skript.effect

import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.sakuragame.eternal.kirramodel.KirraModelAPI
import org.bukkit.event.Event
import java.util.UUID

@Suppress("UNCHECKED_CAST")
class EffectDestroyModel : Effect() {

    private var entityUUID: Expression<String>? = null

    override fun toString(e: Event?, debug: Boolean): String {
        return ""
    }

    override fun init(exprs: Array<Expression<*>>?, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        entityUUID = exprs?.get(0) as Expression<String>
        return true
    }

    override fun execute(e: Event) {
        if (entityUUID == null) {
            return
        }
        val str = entityUUID?.getSingle(e) ?: return
        val entityUUID = UUID.fromString(str)
        val model = KirraModelAPI.getModelByEntityUUID(entityUUID) ?: return
        KirraModelAPI.removeModel(model.id)
    }
}