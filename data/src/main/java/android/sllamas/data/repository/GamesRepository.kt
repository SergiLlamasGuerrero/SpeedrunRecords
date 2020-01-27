package android.sllamas.data.repository

import android.sllamas.data.source.GamesRemoteDataSource

class GamesRepository(private val remoteDataSource: GamesRemoteDataSource) {

    fun getGames() = remoteDataSource.getGames()
}
