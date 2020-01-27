package android.sllamas.speedrunrecords.data.remote.runs

import android.sllamas.data.source.RunsRemoteDataSource
import android.sllamas.domain.Run
import android.sllamas.speedrunrecords.data.remote.WebServiceProvider
import io.reactivex.Single

class RunsRemoteDataSourceImpl : RunsRemoteDataSource {

    override fun getRuns(gameId: String): Single<List<Run>> =
        WebServiceProvider.getService(RunsApi::class.java).getRuns(gameId).map { wrapper -> wrapper.data.map { it.transformToDomain() } }
}