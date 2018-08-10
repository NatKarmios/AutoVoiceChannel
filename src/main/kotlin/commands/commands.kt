package commands

import PREFIX
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import reply
import util.setupGuild

val COMMANDS = listOf(
        Command("setup", "Creates initial category and channels") {
            val success = setupGuild(it.jda.guilds[0]) != null
            it.message.reply(if (success) "channels were set up successfully! :thumbsup:"
            else "channels have already been set up! :upside_down:")
                    .queue()
        }
)

fun handleCommand(evt: MessageReceivedEvent) {
    // Append a space in case the message was purely the command
    val msg = evt.message.contentDisplay + " "

    COMMANDS.forEach {
        if ("$msg ".startsWith("$PREFIX${it.name} "))
            it.func(evt)
    }
}
