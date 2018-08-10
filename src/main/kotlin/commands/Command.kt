package commands

import net.dv8tion.jda.core.events.message.MessageReceivedEvent

data class Command(
        val name: String,
        val description: String,
        val func: (MessageReceivedEvent) -> Unit
)