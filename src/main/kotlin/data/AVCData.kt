package data

import DATA_FILE
import data.channels.ChannelData
import data.roles.RoleData
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException


lateinit var avcData: AVCData

data class AVCData (
        var channelData: ChannelData? = null,
        var roleData   : RoleData?    = null
) {
    init {
        if (::avcData.isInitialized)
            throw RuntimeException("AVC AVCData already initialized!")
        avcData = this
    }

    fun save() {
        File(DATA_FILE).writeText(avcDataAdapter.toJson(this))
    }
}


fun loadAVCData () : AVCData? {
    try {
        val json = File(DATA_FILE).readText()
        val avcData = avcDataAdapter.fromJson(json)
        if (avcData != null) return avcData
    } catch (_: FileNotFoundException) {
        // Ignored
    } catch (e: IOException) {
        e.printStackTrace()
    }
    val data = AVCData()
    data.save()
    return data
}