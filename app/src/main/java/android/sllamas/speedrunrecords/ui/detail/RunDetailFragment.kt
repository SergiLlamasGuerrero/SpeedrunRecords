package android.sllamas.speedrunrecords.ui.detail

import android.os.Bundle
import android.sllamas.speedrunrecords.R
import android.sllamas.speedrunrecords.ui.common.extensions.inflate
import android.sllamas.speedrunrecords.ui.common.extensions.load
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_run_detail.*

class RunDetailFragment: Fragment(), RunDetailPresenter.View {

    private val args: RunDetailFragmentArgs by navArgs()
    private val presenter by lazy { RunDetailPresenter() }

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
    }
}