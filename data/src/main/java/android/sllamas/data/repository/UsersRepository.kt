package android.sllamas.data.repository

import android.sllamas.data.source.UserRemoteDataSource

class UsersRepository(private val remoteDataSource: UserRemoteDataSource) {

    fun getName(userId: String) = remoteDataSource.getName(userId)
}
