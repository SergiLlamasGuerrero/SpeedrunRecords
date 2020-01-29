package android.sllamas.speedrunrecords.data.remote.runs

import android.sllamas.data.source.RunsRemoteDataSource
import android.sllamas.domain.Run
import android.sllamas.speedrunrecords.data.remote.WebServiceProvider
import android.sllamas.speedrunrecords.data.remote.games.GamesApi
import io.reactivex.Single

class RunsRemoteDataSourceImpl(private val service: RunsApi) : RunsRemoteDataSource {

    override fun getRuns(gameId: String): Single<List<Run>> =
        service.getRuns(gameId).map { wrapper -> wrapper.data.map { it.transformToDomain() } }
}