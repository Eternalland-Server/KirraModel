package net.sakuragame.eternal.kirramodel

import org.bukkit.command.CommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.expansion.createHelper
import taboolib.module.chat.colored

@Suppress("SpellCheckingInspection")
@CommandHeader(name = "KirraModels", aliases = ["kmodel", "kmodels"])
object Commands {

    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody
    val reload = subCommand {
        execute<CommandSender> { sender, _, _ ->
            Loader.reload()
            sender.sendMessage("&c[System] &7重载完成.".colored())
        }
    }
}