package android.sllamas.data.source

import io.reactivex.Single

interface UserRemoteDataSource {

    fun getName(userId: String): Single<String>
}