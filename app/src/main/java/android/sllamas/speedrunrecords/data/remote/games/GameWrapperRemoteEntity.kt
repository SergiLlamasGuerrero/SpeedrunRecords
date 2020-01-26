package android.sllamas.speedrunrecords.data.remote.games

import android.sllamas.domain.Game
import com.google.gson.annotations.SerializedName

data class GameWrapperRemoteEntity(
    val data: List<GameRemoteEntity>
)

data class GameRemoteEntity(
    val names: NameRemoteEntity,
    val assets: AssetsRemoteEntity
)

data class NameRemoteEntity(
    val international: String
)

data class AssetsRemoteEntity(
    @SerializedName("cover-small") val coverSmall: CoversRemoteEntity
)

data class CoversRemoteEntity(
    val uri: String
)

fun GameRemoteEntity.transformToDomain() = Game(names.international, assets.coverSmall.uri)