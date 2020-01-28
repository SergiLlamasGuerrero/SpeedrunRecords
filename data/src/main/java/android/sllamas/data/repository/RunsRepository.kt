package android.sllamas.data.repository

import android.sllamas.data.source.RunsRemoteDataSource

class RunsRepository(private val remoteDataSource: RunsRemoteDataSource) {

    fun getRuns(gameId: String) = remoteDataSource.getRuns(gameId)
}