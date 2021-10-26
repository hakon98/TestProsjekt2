package no.usn.ruud.testprosjekt2.ui.record

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import no.usn.ruud.testprosjekt2.R
import no.usn.ruud.testprosjekt2.database.Exercise
import no.usn.ruud.testprosjekt2.database.FitGuuyDatabase
import no.usn.ruud.testprosjekt2.database.WorkoutDatabaseDao
import no.usn.ruud.testprosjekt2.database.WorkoutInDb
import no.usn.ruud.testprosjekt2.databinding.FragmentRecordBinding

class RecordFragment : Fragment() {

    private lateinit var recordViewModel: RecordViewModel
    private lateinit var recordViewModelFactory: RecordViewModelFactory
    private lateinit var linLayoutMgr: RecyclerView.LayoutManager

    private lateinit var recordRecyclerView: RecyclerView
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
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val scope = viewLifecycleOwner.lifecycleScope
        val mContext = getActivity()

        val application = requireNotNull(this.activity).application
        val dataSource = FitGuuyDatabase.getInstance( application, viewLifecycleOwner.lifecycleScope).workoutDatabaseDao

        recordViewModelFactory = RecordViewModelFactory(dataSource, application)
        recordViewModel =
            ViewModelProvider(this, recordViewModelFactory).get(RecordViewModel::class.java)
        //  linLayoutMgr.setLayoutManager(new LinearLayoutManager(this));
        linLayoutMgr = LinearLayoutManager(context)
        val recordAdapter = RecordListAdapter()
        binding.recordRecylerView.adapter = recordAdapter
        recordViewModel.exerciseplan.observe(viewLifecycleOwner, Observer {
            it?.let {
                recordAdapter.submitList(it)
            }
        })
        recordViewModel.lastWorkout.observe(viewLifecycleOwner, Observer{
            recordAdapter.lastWorkout = it
        })
        recordRecyclerView = binding.recordRecylerView.apply {
            setHasFixedSize(false)
            layoutManager = linLayoutMgr
            adapter = recordAdapter
        }

        /*
        recordViewModel._workout.observe(viewLifecycleOwner, Observer {
            it?.let {
                recordAdapter.submitList(List<WorkoutInDb>(it))
            }
        })

         */
        dataSource.let { database ->
            scope.launch {
                //deleteAll(database)
                //populateDatabase(database)
            }
            return root
        }


    }
    companion object {
        @JvmStatic
        suspend fun populateDatabase(workoutDatabaseDao: WorkoutDatabaseDao) {

            // Delete all content here

            //Legg til eksempler på ord
            Log.i("RecordFragment", "populateDatabase kjørt")
            var exercise = Exercise()
            val exercises = listOf("Benkpress","Knebøy","Markløft","Roing","Nedtrekk","Biceps curl")
            exercises.forEach {
                exercise.partOfName = "default"
                exercise.name = it
                exercise.reps = 5
                exercise.sets = 5
                workoutDatabaseDao.insert(exercise)
            }

            Log.i("RecordFragment", "populateDatabase.insert kjørt")

            var workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)


        }

        suspend fun deleteAll(workoutDatabaseDao: WorkoutDatabaseDao) {
            workoutDatabaseDao.deleteAllWor()
            workoutDatabaseDao.deleteAllEx()
            workoutDatabaseDao.clearETab()
            workoutDatabaseDao.clearWTab()
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