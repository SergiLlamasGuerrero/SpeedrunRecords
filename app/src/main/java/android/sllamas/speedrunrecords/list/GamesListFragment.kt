package android.sllamas.speedrunrecords.list

import android.os.Bundle
import android.sllamas.speedrunrecords.R
import android.sllamas.speedrunrecords.common.extensions.inflate
import android.sllamas.speedrunrecords.list.adapter.GamesAdapter
import android.sllamas.speedrunrecords.list.model.Game
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_games_list.*

class GamesListFragment : Fragment(), GamesListPresenter.View {

    private val presenter by lazy { GamesListPresenter() }
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
        gamesRecyclerView.adapter = gamesAdapter

        presenter.onViewsInitialized()
    }

    override fun populateList(games: MutableList<Game>) {
        gamesAdapter.items = games
    }
}