package android.sllamas.usecases

import android.sllamas.data.repository.GamesRepository
import io.reactivex.Scheduler

class GetGames(
    private val gamesRepository: GamesRepository,
    private val subscribeThread: Scheduler,
    private val observeThread: Scheduler
) {

    fun execute() = gamesRepository.getGames().subscribeOn(subscribeThread).observeOn(observeThread)
}
