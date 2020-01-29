package android.sllamas.speedrunrecords.data.remote.users

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {

    @GET("v1/users/{userId}")
    fun getUser(@Path("userId") userId: String): Single<UserWrapperRemoteEntity>
}
