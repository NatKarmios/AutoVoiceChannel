package data.voice

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import net.dv8tion.jda.core.entities.VoiceChannel
import java.util.concurrent.TimeUnit

data class TemporaryVoiceChannel (
        val channel: VoiceChannel
) {
    private lateinit var deletionJob: Job

    init {
        scheduleDeletion()
    }

    fun scheduleDeletion() {
        deletionJob = launch {
            delay(30, TimeUnit.SECONDS)
            if (isActive)
                channel.delete().queue()
        }
    }

    fun cancelDeletion() {
        deletionJob.cancel()
        deletionJob
    }
}