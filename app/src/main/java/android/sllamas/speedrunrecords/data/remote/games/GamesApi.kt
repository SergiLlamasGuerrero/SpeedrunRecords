package android.sllamas.speedrunrecords.data.remote.games

import io.reactivex.Single
import retrofit2.http.GET

interface GamesApi {

    @GET("v1/games")
    fun getGames(): Single<GameWrapperRemoteEntity>
}
