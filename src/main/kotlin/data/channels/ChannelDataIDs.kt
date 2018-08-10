package data.channels
import com.squareup.moshi.Json
import net.dv8tion.jda.core.JDA


val ChannelData.IDs: ChannelDataIDs
    get() = ChannelDataIDs(
            this.guild.idLong,
            this.category.idLong,
            this.menuChannel.idLong,
            this.logChannel.idLong,
            this.menuMessageID
    )

data class ChannelDataIDs (
        @Json(name = "guild_id")        val guildID      : Long,
        @Json(name = "category_id")     val categoryID   : Long,
        @Json(name = "menu_channel_id") val menuChannelID: Long,
        @Json(name = "log_channel_id")  val logChannelID : Long,
        @Json(name = "menu_message_id") val menuMessageID: Long
) {
    fun toChannelData(jda: JDA): ChannelData {
        val menuChannel = jda.getTextChannelById(menuChannelID)

        return ChannelData(
                jda.getGuildById(guildID),
                jda.getCategoryById(categoryID),
                menuChannel,
                jda.getTextChannelById(logChannelID),
                menuMessageID
        )
    }
}