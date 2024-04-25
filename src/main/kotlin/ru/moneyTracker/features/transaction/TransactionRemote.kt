package ru.moneyTracker.features.transaction

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.Date


@Serializable
class TransactionRemote(
    val name: String,
    val amount: Double,

    @Serializable(with = DateSerializer::class)
    val date: Date,
    val type: String,
    val description: String,
)

@Serializable
class TransactionReceiveRemote(
    val token: String,
    val transaction: TransactionRemote
)


@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Date::class)
class DateSerializer : KSerializer<Date> {
    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Date {
        return Date(decoder.decodeLong())
    }

    //https://stackoverflow.com/questions/67305843/kotlinx-serialization-deserializing-dates почитать про лучшие варианты
}