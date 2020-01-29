package android.sllamas.speedrunrecords

import android.app.Application
import android.sllamas.data.repository.GamesRepository
import android.sllamas.data.repository.RunsRepository
import android.sllamas.data.repository.UsersRepository
import android.sllamas.data.source.GamesRemoteDataSource
import android.sllamas.data.source.RunsRemoteDataSource
import android.sllamas.data.source.UserRemoteDataSource
import android.sllamas.speedrunrecords.data.remote.WebServiceProvider
import android.sllamas.speedrunrecords.data.remote.games.GamesApi
import android.sllamas.speedrunrecords.data.remote.games.GamesRemoteDataSourceImpl
import android.sllamas.speedrunrecords.data.remote.runs.RunsApi
import android.sllamas.speedrunrecords.data.remote.runs.RunsRemoteDataSourceImpl
import android.sllamas.speedrunrecords.data.remote.users.UsersApi
import android.sllamas.speedrunrecords.data.remote.users.UsersRemoteDataSourceImpl
import android.sllamas.speedrunrecords.ui.detail.RunDetailPresenter
import android.sllamas.speedrunrecords.ui.list.GamesListPresenter
import android.sllamas.usecases.GetGames
import android.sllamas.usecases.GetRunsByGameId
import android.sllamas.usecases.GetUserNameById
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        printLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, usecasesModule))
    }
}

private val appModule = module {
    single { WebServiceProvider.getService(GamesApi::class.java) }
    single { WebServiceProvider.getService(RunsApi::class.java) }
    single { WebServiceProvider.getService(UsersApi::class.java) }
    factory<GamesRemoteDataSource> { GamesRemoteDataSourceImpl(get()) }
    factory<RunsRemoteDataSource> { RunsRemoteDataSourceImpl(get()) }
    factory<UserRemoteDataSource> { UsersRemoteDataSourceImpl(get()) }
    factory { GamesListPresenter(get()) }
    factory { RunDetailPresenter(get(), get()) }
}

private val dataModule = module {
    factory { GamesRepository(get()) }
    factory { RunsRepository(get()) }
    factory { UsersRepository(get()) }
}

private val usecasesModule = module {
    single(named("subscribeThread")) { Schedulers.io() }
    single(named("observeThread")) { AndroidSchedulers.mainThread() }
    factory { GetRunsByGameId(get(), get(named("subscribeThread")), get(named("observeThread"))) }
    factory { GetGames(get(), get(named("subscribeThread")), get(named("observeThread"))) }
    factory { GetUserNameById(get(), get(named("subscribeThread")), get(named("observeThread"))) }
}