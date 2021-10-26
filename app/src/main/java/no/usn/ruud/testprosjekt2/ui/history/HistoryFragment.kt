package no.usn.ruud.testprosjekt2.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import no.usn.ruud.testprosjekt2.database.FitGuuyDatabase
import no.usn.ruud.testprosjekt2.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var historyViewModelFactory: HistoryViewModelFactory

    private lateinit var linLayoutMgr: RecyclerView.LayoutManager
    private lateinit var historyRecyclerView: RecyclerView
    private var _binding: FragmentHistoryBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mContext = getActivity()

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*
        historyViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        */
        val application = requireNotNull(this.activity).application
        val dataSource = FitGuuyDatabase.getInstance(application, viewLifecycleOwner.lifecycleScope).workoutDatabaseDao

        historyViewModelFactory = HistoryViewModelFactory(dataSource, application)
        historyViewModel =
            ViewModelProvider(this, historyViewModelFactory).get(HistoryViewModel::class.java)
      //  linLayoutMgr.setLayoutManager(new LinearLayoutManager(this));
        linLayoutMgr = LinearLayoutManager(context)
        val historyAdapter = HistoryListAdapter()
        binding.workoutHistoryRecylerView.adapter = historyAdapter
        historyViewModel.nights.observe(viewLifecycleOwner,Observer {
            it?.let {
                historyAdapter.submitList(it)
            }
        })
        /*
        historyViewModel.workout.observe(viewLifecycleOwner, {
            Log.i("HistoryFragment", "viewModel observer: ${ historyViewModel.workout.value.toString() }")
            historyAdapter = HistoryListAdapter( mContext as Context, historyViewModel.workout)
            //(historyAdapter as HistoryListAdapter).getHistoryListData(historyViewModel.workout)
        })


        historyViewModel.workout.observe(viewLifecycleOwner, {
            //it = historyViewModel.workout.value
            (historyAdapter as HistoryListAdapter).notifyDataSetChanged()
        })


        Log.i("HistoryFragment", "viewModel initiert: ${ historyViewModel.workout.value.toString() }")

         */

        historyRecyclerView = binding.workoutHistoryRecylerView.apply {
            setHasFixedSize(false)
            layoutManager = linLayoutMgr
            adapter = historyAdapter
        }

        binding.historyViewModel = historyViewModel

        binding.lifecycleOwner = this

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}