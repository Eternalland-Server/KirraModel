package net.sakuragame.eternal.kirramodel.compat.skript.event

import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import org.bukkit.event.Event

@Name("On Model Interact")
@Description("")
class ModelInteractEvent : SkriptEvent() {

    override fun toString(e: Event?, debug: Boolean) = null

    override fun init(args: Array<Literal<*>>?, matchedPattern: Int, parseResult: SkriptParser.ParseResult?) = true

    override fun check(e: Event): Boolean {
        if (e is net.sakuragame.eternal.kirramodel.event.ModelInteractEvent) {
            return true
        }
        return false
    }
}