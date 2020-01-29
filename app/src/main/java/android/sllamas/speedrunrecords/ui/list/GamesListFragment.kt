package android.sllamas.speedrunrecords.ui.list

import android.os.Bundle
import android.sllamas.speedrunrecords.R
import android.sllamas.speedrunrecords.ui.common.extensions.inflate
import android.sllamas.speedrunrecords.ui.common.extensions.showToast
import android.sllamas.speedrunrecords.ui.list.adapter.GamesAdapter
import android.sllamas.speedrunrecords.ui.list.model.GameViewEntity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_games_list.*
import org.koin.android.ext.android.inject

class GamesListFragment : Fragment(), GamesListPresenter.View {

    private lateinit var navController: NavController
    private val presenter by inject<GamesListPresenter>()
    private val gamesAdapter by lazy { GamesAdapter(presenter::onGameClicked) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_games_list)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
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

    override fun showLoading() {
        gamesRecyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        gamesRecyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun showError() {
        context!!.showToast(R.string.failed_to_load_games_list)
    }

    override fun populateList(games: MutableList<GameViewEntity>) {
        gamesAdapter.items = games
    }

    override fun showRunDetailScreen(game: GameViewEntity) {
        val action = GamesListFragmentDirections.actionRecordsListFragmentToRunDetailFragment(game)
        navController.navigate(action)
    }
}