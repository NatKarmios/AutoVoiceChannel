package util

import EMOTE_LIST
import data.avcData
import data.voice.TemporaryVoiceChannel
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.entities.VoiceChannel
import java.util.concurrent.TimeUnit

var count = 0
val voiceRoomMap = HashMap<Long, TemporaryVoiceChannel>()

fun handleReaction(user: User, emoteName: String) {
    val idx = EMOTE_LIST.indexOf(emoteName)
    if (idx == -1) return

    val response = if (user.canCreateRoom()) {
        createVoiceRoom(user, idx)
        user.startCooldown()
        "done! :ok_hand:"
    } else {
        val timeLeft = user.secondsUntilCooldown as Long
        "please wait ${timeLeft/60}:${timeLeft%60} before making another voice channel! :timer:"
    }

    avcData.channelData!!.menuChannel.sendMessage("${user.asMention}, $response").queue {
        it.delete().queueAfter(5, TimeUnit.SECONDS)
    }
}

fun handleJoinOrLeave(channel: VoiceChannel) {
    val voiceRoom = voiceRoomMap[channel.idLong]
    if (channel.members.size > 0)
        voiceRoom?.cancelDeletion()
    else
        voiceRoom?.scheduleDeletion()
}

fun createVoiceRoom(user: User, limit: Int) {
    avcData.channelData!!.category.createVoiceChannel("Voice Room #${++count}")
            .setUserlimit(limit)
            .queue { voiceRoomMap[it.idLong] = TemporaryVoiceChannel(it as VoiceChannel) }
}
