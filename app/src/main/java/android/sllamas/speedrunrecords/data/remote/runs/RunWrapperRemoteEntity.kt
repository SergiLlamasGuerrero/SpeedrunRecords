package android.sllamas.speedrunrecords.data.remote.runs

import android.sllamas.domain.Run
import com.google.gson.annotations.SerializedName

data class RunWrapperRemoteEntity (
    val data: List<RunRemoteEntity>
)

data class RunRemoteEntity(
    val videos: VideosWrapperRemoteEntity,
    val players: List<PlayerRemoteEntity>,
    val times: TimesRemoteEntity
)

data class VideosWrapperRemoteEntity(
    val links: List<LinkRemoteEntity>
)

data class LinkRemoteEntity(
    val uri: String
)

data class PlayerRemoteEntity(
    val id: String?,
    val name: String?
)

data class TimesRemoteEntity(
    @SerializedName("primary_t") val timeInSeconds: Long
)

fun RunRemoteEntity.transformToDomain() = Run(
    players.first().id,
    players.first().name,
    times.timeInSeconds,
    videos.links.firstOrNull()?.uri
)