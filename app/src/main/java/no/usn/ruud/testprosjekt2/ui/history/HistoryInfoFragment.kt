package no.usn.ruud.testprosjekt2.ui.history

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import no.usn.ruud.testprosjekt2.R

class HistoryInfoFragment : Fragment(R.layout.fragment_history_info) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toInfoPageAnimation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = toInfoPageAnimation
        sharedElementReturnTransition = toInfoPageAnimation
    }

}