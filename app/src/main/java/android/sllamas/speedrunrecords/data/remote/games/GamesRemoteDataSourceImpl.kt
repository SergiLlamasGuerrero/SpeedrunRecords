package android.sllamas.speedrunrecords.data.remote.games

import android.sllamas.data.source.GamesRemoteDataSource
import android.sllamas.domain.Game
import android.sllamas.speedrunrecords.data.remote.WebServiceProvider
import io.reactivex.Single

class GamesRemoteDataSourceImpl : GamesRemoteDataSource {

    override fun getGames(): Single<List<Game>> =
        WebServiceProvider.getService(GamesApi::class.java).getGames().map { wrapper -> wrapper.data.map { it.transformToDomain() } }
}