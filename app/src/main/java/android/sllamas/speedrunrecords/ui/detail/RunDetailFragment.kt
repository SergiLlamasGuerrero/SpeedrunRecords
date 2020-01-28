package android.sllamas.speedrunrecords.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.sllamas.speedrunrecords.R
import android.sllamas.speedrunrecords.ui.common.extensions.inflate
import android.sllamas.speedrunrecords.ui.common.extensions.load
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_run_detail.*
import org.koin.android.ext.android.inject

class RunDetailFragment : Fragment(), RunDetailPresenter.View {

    private val args: RunDetailFragmentArgs by navArgs()
    private val presenter by inject<RunDetailPresenter>()

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

    override fun showPlayerName(playerName: String) {
        playerTextView.visibility = View.VISIBLE
        playerTextView.text = buildSpannedString {
            bold { append("${getString(R.string.player_title)} ") }
            append(playerName)
        }
    }

    override fun showRunTime(time: Long) {
        runTimeTextView.visibility = View.VISIBLE
        runTimeTextView.text = buildSpannedString {
            bold { append("${getString(R.string.runtime_title)} ") }
            append(time.toString())
            append(" ${getString(R.string.seconds)}")
        }
    }

    override fun showVideoButton(url: String) {
        watchVideoTextView.visibility = View.VISIBLE
        videoImageButton.visibility = View.VISIBLE
        videoImageButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }
}