import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.requests.restaction.MessageAction

fun Message.reply(msg: String) =
        this.channel.sendMessage("${this.author.asMention}, $msg") as MessageAction