package data

import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import data.channels.ChannelData
import data.channels.ChannelDataIDs
import data.channels.IDs
import net.dv8tion.jda.core.JDA


lateinit var moshi                : Moshi
lateinit var avcDataAdapter          : JsonAdapter<AVCData>
lateinit var channelDataIDsAdapter: JsonAdapter<ChannelDataIDs>


fun buildMoshi(jda: JDA) {
    moshi = Moshi.Builder()
            .add(ChannelData::class.java, ChannelDataAdapter(jda))
            .add(KotlinJsonAdapterFactory())
            .build()

    avcDataAdapter        = moshi.adapter(AVCData::class.java)
    channelDataIDsAdapter = moshi.adapter(ChannelDataIDs::class.java)
}

private class ChannelDataAdapter(val jda: JDA) : JsonAdapter<ChannelData>() {

    @FromJson
    override fun fromJson(reader: JsonReader?): ChannelData? =
            if (reader == null) null
            else channelDataIDsAdapter.fromJson(reader)?.toChannelData(jda)

    @ToJson
    override fun toJson(writer: JsonWriter?, value: ChannelData?) {
        if (writer != null)
            channelDataIDsAdapter.toJson(writer, value?.IDs)
    }
}