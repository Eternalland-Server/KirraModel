package net.sakuragame.eternal.kirramodel

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.expansion.createHelper
import taboolib.module.chat.colored

@Suppress("SpellCheckingInspection")
@CommandHeader(name = "KirraModels", aliases = ["model", "kmodel", "kmodels"])
object Commands {

    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody
    val reload = subCommand {
        literal("recycle") {
            execute<CommandSender> { sender, _, _ ->
                Loader.reload(recycle = true)
                sender.sendMessage("&c[System] &7重载完成. (已删除所有实体)".colored())
            }
        }
        execute<CommandSender> { sender, _, _ ->
            Loader.reload()
            sender.sendMessage("&c[System] &7重载完成.".colored())
        }
    }

    @CommandBody
    val createTemp = subCommand {
        dynamic(commit = "tempId") {
            execute<Player> { player, _, argument ->
                val originModel = KirraModelAPI.models[argument]
                if (originModel == null) {
                    player.sendMessage("&c[System] &7原模型不存在.".colored())
                    return@execute
                }
                KirraModelAPI.createTempModel(player.location, originModel)
                player.sendMessage("&c[System] &7模型创建成功.".colored())
            }
        }
    }
}