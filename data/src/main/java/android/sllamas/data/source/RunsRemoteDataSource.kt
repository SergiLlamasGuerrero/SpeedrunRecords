package android.sllamas.data.source

import android.sllamas.domain.Run
import io.reactivex.Single

interface RunsRemoteDataSource {

    fun getRuns(gameId: String): Single<List<Run>>
}