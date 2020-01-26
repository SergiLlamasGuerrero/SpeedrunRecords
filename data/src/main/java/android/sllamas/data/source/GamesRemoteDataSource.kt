package android.sllamas.data.source

import android.sllamas.domain.Game
import io.reactivex.Single

interface GamesRemoteDataSource {

    fun getGames(): Single<List<Game>>
}