package android.sllamas.speedrunrecords.ui.detail

import android.os.Bundle
import android.sllamas.speedrunrecords.R
import android.sllamas.speedrunrecords.ui.common.extensions.inflate
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class RunDetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_run_detail)
}