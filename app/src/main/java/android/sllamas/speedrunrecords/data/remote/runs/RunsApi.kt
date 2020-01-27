package android.sllamas.speedrunrecords.data.remote.runs

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RunsApi {

    @GET("v1/runs")
    fun getRuns(@Query("game") gameId: String): Single<RunWrapperRemoteEntity>
}