package android.sllamas.speedrunrecords.ui.detail

import android.os.Bundle
import android.sllamas.data.repository.RunsRepository
import android.sllamas.speedrunrecords.R
import android.sllamas.speedrunrecords.data.remote.runs.RunsRemoteDataSourceImpl
import android.sllamas.speedrunrecords.ui.common.extensions.inflate
import android.sllamas.speedrunrecords.ui.common.extensions.load
import android.sllamas.usecases.GetRunsByGameId
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_run_detail.*

class RunDetailFragment : Fragment(), RunDetailPresenter.View {

    private val args: RunDetailFragmentArgs by navArgs()
    private val presenter by lazy {
        RunDetailPresenter(
            GetRunsByGameId(
                RunsRepository(
                    RunsRemoteDataSourceImpl()
                ), Schedulers.io(), AndroidSchedulers.mainThread()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_run_detail)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
    }

    override fun initializeViews() {
        gameNameTextView.text = args.game.name
        logoImageView.load(args.game.logoUrl)
        presenter.onReadyToGetRunInfo(args.game.id)
    }
}