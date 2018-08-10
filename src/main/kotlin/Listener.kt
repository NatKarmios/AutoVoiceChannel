import commands.handleCommand
import data.avcData
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import util.handleJoinOrLeave
import util.handleReaction


class Listener : ListenerAdapter() {
    override fun onMessageReceived(evt: MessageReceivedEvent) {
        val jda = evt.jda

        val author = evt.author
        val message = evt.message

        if (jda.selfUser.idLong != author.idLong) {
            println(message.contentDisplay)
            handleCommand(evt)
        }
    }

    override fun onMessageReactionAdd(evt: MessageReactionAddEvent) {
        if (evt.user.idLong != evt.jda.selfUser.idLong && evt.messageIdLong == avcData.channelData?.menuMessageID) {
            evt.reaction.removeReaction(evt.user).complete()
            handleReaction(evt.user, evt.reactionEmote.name)
        }
    }

    override fun onGuildVoiceJoin(evt: GuildVoiceJoinEvent?) {
        handleJoinOrLeave(evt!!.channelJoined)
    }

    override fun onGuildVoiceLeave(evt: GuildVoiceLeaveEvent?) {
        handleJoinOrLeave(evt!!.channelLeft)
    }
}