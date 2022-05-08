package net.sakuragame.eternal.kirramodel.compat.skript.effect

import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.sakuragame.eternal.justmessage.api.MessageAPI
import org.bukkit.entity.Player
import org.bukkit.event.Event
import taboolib.module.chat.colored

@Suppress("UNCHECKED_CAST")
class ActionTextEffect : Effect() {

    private var text: Expression<String>? = null
    private var player: Expression<Player>? = null

    override fun toString(e: Event?, debug: Boolean): String? {
        return null
    }

    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        text = exprs[0] as Expression<String>
        player = exprs[1] as Expression<Player>
        return true
    }

    override fun execute(e: Event) {
        val player = player?.getSingle(e) ?: return
        val text = text?.getSingle(e) ?: return
        MessageAPI.sendActionTip(player, text.colored())
    }
}