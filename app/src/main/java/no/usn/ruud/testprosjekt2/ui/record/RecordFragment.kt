package no.usn.ruud.testprosjekt2.ui.record

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.launch
import no.usn.ruud.testprosjekt2.database.WorkoutDatabase
import no.usn.ruud.testprosjekt2.database.WorkoutDatabaseDao
import no.usn.ruud.testprosjekt2.database.WorkoutInDb
import no.usn.ruud.testprosjekt2.databinding.FragmentRecordBinding

class RecordFragment : Fragment() {

    private lateinit var recordViewModel: RecordViewModel
    private var _binding: FragmentRecordBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var textView: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val scope = viewLifecycleOwner.lifecycleScope
        val mContext = getActivity()
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val application = requireNotNull(this.activity).application
        val dataSource = WorkoutDatabase.getInstance(
            application,
            viewLifecycleOwner.lifecycleScope
        ).workoutDatabaseDao
        var word = WorkoutInDb()
        word.type = "Knebøy"
        scope.launch {
            dataSource.insert(word)
        }
        dataSource.let { database ->
            scope.launch {
                populateDatabase(database)
            }

            return root
        }
    }
    companion object {
        @JvmStatic
        suspend fun populateDatabase(workoutDatabaseDao: WorkoutDatabaseDao) {
            // Delete all content here
            //workoutDatabaseDao.deleteAll()
            //workoutDatabaseDao.deleteAll()
            //Legg til eksempler på ord
            Log.i("RecordFragment", "populateDatabase kjørt")
            var word = WorkoutInDb()
            word.type = "Benkpress"
            workoutDatabaseDao.insert(word)
            word = WorkoutInDb()
            word.type = "Knebøy"
            workoutDatabaseDao.insert(word)
            word = WorkoutInDb()
            word.type = "Markløft"
            workoutDatabaseDao.insert(word)
        }
    }

    override fun onStart() {
        super.onStart()
//        recordViewModel =
//            ViewModelProvider(this).get(RecordViewModel::class.java)
//        recordViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}