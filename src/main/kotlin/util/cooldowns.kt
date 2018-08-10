package util

import net.dv8tion.jda.core.entities.User
import java.time.Duration
import java.time.LocalDateTime

val userCooldownMap = HashMap<Long, LocalDateTime>()

fun User.canCreateRoom(): Boolean {
    val cooldownEnd = userCooldownMap[idLong]
    val now = LocalDateTime.now()
    return cooldownEnd == null || now > cooldownEnd
}

val User.secondsUntilCooldown: Long?
    get() =
        if (userCooldownMap.contains(idLong)) Duration.between(LocalDateTime.now(), userCooldownMap[idLong]).seconds
        else null

fun User.startCooldown() {
    userCooldownMap[idLong] = LocalDateTime.now().plusMinutes(5)
}