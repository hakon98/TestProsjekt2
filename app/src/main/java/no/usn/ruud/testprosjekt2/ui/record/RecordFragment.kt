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
        binding.saveButton.setOnClickListener{
            val childCount = binding.recordRecylerView.getChildCount()
            var i = 0;
            var childViewList: MutableList<View> = mutableListOf()
            while (i<childCount) {
                childViewList.add(binding.recordRecylerView.getChildAt(i))
            }
            //TODO(Håkon) Legg inn i ViewModel
            dataSource.let { database ->
                scope.launch {
                    dataSource.insert(getDataFromView(childViewList))
                }
            }

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
        }
        return root


    }

    fun getDataFromView(list:MutableList<View>) : WorkoutInDb {
        //TODO(Alle): Lag en metode som henter reps fra alle knappene og putter det i en string.
        val newWorkout = WorkoutInDb()
        newWorkout.type1 = list[1].findViewById<TextView>(R.id.txtExerciseName).toString()
        newWorkout.reps1 = list[1].findViewById<TextView>(R.id.figure_1).toString()
        newWorkout.weight1 = list[1].findViewById<TextView>(R.id.txtCurrentWeight).toString()
        newWorkout.type2 = list[2].findViewById<TextView>(R.id.txtExerciseName).toString()
        newWorkout.reps2 = list[2].findViewById<TextView>(R.id.figure_1).toString()
        newWorkout.weight2 = list[2].findViewById<TextView>(R.id.txtCurrentWeight).toString()
        newWorkout.type3 = list[3].findViewById<TextView>(R.id.txtExerciseName).toString()
        newWorkout.reps3 = list[3].findViewById<TextView>(R.id.figure_1).toString()
        newWorkout.weight3 = list[3].findViewById<TextView>(R.id.txtCurrentWeight).toString()
        newWorkout.type4 = list[4].findViewById<TextView>(R.id.txtExerciseName).toString()
        newWorkout.reps4 = list[4].findViewById<TextView>(R.id.figure_1).toString()
        newWorkout.weight4 = list[4].findViewById<TextView>(R.id.txtCurrentWeight).toString()
        newWorkout.type5 = list[5].findViewById<TextView>(R.id.txtExerciseName).toString()
        newWorkout.reps5 = list[5].findViewById<TextView>(R.id.figure_1).toString()
        newWorkout.weight5 = list[5].findViewById<TextView>(R.id.txtCurrentWeight).toString()
        newWorkout.type6 = list[6].findViewById<TextView>(R.id.txtExerciseName).toString()
        newWorkout.reps6 = list[6].findViewById<TextView>(R.id.figure_1).toString()
        newWorkout.weight6 = list[6].findViewById<TextView>(R.id.txtCurrentWeight).toString()
        return newWorkout
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

private fun <E> List<E>.add(childAt: E) {

}


