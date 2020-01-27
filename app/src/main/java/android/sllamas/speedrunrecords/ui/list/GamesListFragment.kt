package android.sllamas.speedrunrecords.ui.list

import android.os.Bundle
import android.sllamas.data.repository.GameRepository
import android.sllamas.domain.Game
import android.sllamas.speedrunrecords.R
import android.sllamas.speedrunrecords.data.remote.games.GamesRemoteDataSourceImpl
import android.sllamas.speedrunrecords.ui.common.extensions.inflate
import android.sllamas.speedrunrecords.ui.common.extensions.showToast
import android.sllamas.speedrunrecords.ui.list.adapter.GamesAdapter
import android.sllamas.usecases.GetGames
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_games_list.*

class GamesListFragment : Fragment(), GamesListPresenter.View {

    private val presenter by lazy { GamesListPresenter(GetGames(GameRepository(GamesRemoteDataSourceImpl()), Schedulers.io(), AndroidSchedulers.mainThread())) }
    private val gamesAdapter by lazy { GamesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_games_list)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
    }

    override fun onDestroyView() {
        presenter.detachView()

        super.onDestroyView()
    }

    override fun initializeViews() {
        val dividerDrawable = ContextCompat.getDrawable(context!!, R.drawable.recycler_view_divider)
        gamesRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            ).apply { setDrawable(dividerDrawable!!) }
        )
        gamesRecyclerView.adapter = gamesAdapter

        presenter.onViewsInitialized()
    }

    override fun showError() {
        context!!.showToast(R.string.failed_to_load_games_list)
    }

    override fun populateList(games: MutableList<Game>) {
        gamesAdapter.items = games
    }
}