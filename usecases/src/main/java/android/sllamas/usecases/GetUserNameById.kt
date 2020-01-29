package android.sllamas.usecases

import android.sllamas.data.repository.UsersRepository
import io.reactivex.Scheduler

class GetUserNameById(
    private val userRepository: UsersRepository,
    private val subscribeThread: Scheduler,
    private val observeThread: Scheduler
) {

    fun execute(userId: String) = userRepository.getName(userId).subscribeOn(subscribeThread).observeOn(observeThread)
}
