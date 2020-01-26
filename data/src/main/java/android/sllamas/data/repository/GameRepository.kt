package android.sllamas.data.repository

import android.sllamas.data.source.GamesRemoteDataSource

class GameRepository(private val remoteDataSource: GamesRemoteDataSource) {

    fun getGames() = remoteDataSource.getGames()
}
