package util

import EMOTE_LIST
import MENU_MESSAGE
import data.avcData
import data.channels.ChannelData
import net.dv8tion.jda.core.entities.Category
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.TextChannel

fun setupGuild(guild: Guild): ChannelData? {
    // If channels AVCData is already initialised, cancel
    if (avcData.channelData != null)
        return null

    val guildController = guild.controller

    val category    = guildController.createCategory("Voice Channels").complete() as Category
    val menuChannel = category.createTextChannel("voice_channel_menu").complete() as TextChannel
    val logChannel  = category.createTextChannel("voice_channel_log").complete()  as TextChannel

    val menuMessage = menuChannel.sendMessage(MENU_MESSAGE).complete()
    EMOTE_LIST.forEach { menuMessage.addReaction(it).queue() }

    val channelData = ChannelData(guild, category, menuChannel, logChannel, menuMessage.idLong)
    avcData.channelData = channelData
    avcData.save()
    return channelData
}
