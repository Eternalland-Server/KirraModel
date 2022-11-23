package net.sakuragame.eternal.kirramodel.compat.skript.expression

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.skript.log.ErrorQuality
import ch.njol.util.Kleenean
import net.sakuragame.eternal.kirramodel.event.ModelInteractEvent
import org.bukkit.event.Event

class ExprModelUUID : SimpleExpression<String>() {

    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        if (!parser.isCurrentEvent(ModelInteractEvent::class.java)) {
            Skript.error("Cannot use 'model-uuid' outside of ModelInteractEvent", ErrorQuality.SEMANTIC_ERROR)
            return false
        }
        return true
    }

    override fun toString(e: Event?, debug: Boolean) = null
    override fun isSingle(): Boolean {
        return true
    }

    override fun getReturnType(): Class<String> {
        return String::class.java
    }

    override fun get(e: Event?): Array<String>? {
        val interactEvent = e as? ModelInteractEvent ?: return null
        return arrayOf(interactEvent.model.kEntity?.entity?.normalizeUniqueId?.toString() ?: return null)
    }
}