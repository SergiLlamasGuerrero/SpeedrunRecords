package android.sllamas.speedrunrecords.list

import android.os.Bundle
import android.sllamas.speedrunrecords.R
import android.sllamas.speedrunrecords.common.inflate
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class RecordsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_records_list)
}