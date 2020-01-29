package android.sllamas.speedrunrecords.data.remote.users

data class UserWrapperRemoteEntity(
    val data: DataRemoteEntity
)

data class DataRemoteEntity(
    val names: NamesRemoteEntity
)

data class NamesRemoteEntity(
    val international: String?
)