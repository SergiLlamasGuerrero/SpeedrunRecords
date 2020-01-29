package android.sllamas.speedrunrecords.data.remote.users

import android.sllamas.data.source.UserRemoteDataSource
import io.reactivex.Single

class UsersRemoteDataSourceImpl(private val service: UsersApi): UserRemoteDataSource {

    override fun getName(userId: String): Single<String> =
        service.getUser(userId).map { it.data.names.international }
}