package data.channels

import data.avcData
import net.dv8tion.jda.core.entities.Category
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.TextChannel

data class ChannelData (
        val guild        : Guild,
        val category     : Category,
        val menuChannel  : TextChannel,
        val logChannel   : TextChannel,
        val menuMessageID: Long
)