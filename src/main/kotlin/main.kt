// https://discordapp.com/api/oauth2/authorize?client_id=477071659858132993&scope=bot&permissions=10256

import data.AVCData
import data.buildMoshi
import data.avcDataAdapter
import data.loadAVCData
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.AccountType
import java.io.File
import javax.security.auth.login.LoginException


fun main(args: Array<String>) {
    try {
        val jda = JDABuilder(AccountType.BOT)
                .setToken("INSERT TOKEN HERE")  // TODO fix
                .addEventListener(Listener())
                .buildBlocking()

        buildMoshi(jda)
        loadAVCData()

    } catch (e: LoginException) {
        e.printStackTrace()
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
}