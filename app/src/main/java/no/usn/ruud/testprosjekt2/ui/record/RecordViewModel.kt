package no.usn.ruud.testprosjekt2.ui.record

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import no.usn.ruud.testprosjekt2.database.Exercise
import no.usn.ruud.testprosjekt2.database.WorkoutDatabaseDao
import no.usn.ruud.testprosjekt2.database.WorkoutInDb

class RecordViewModel (dataSource: WorkoutDatabaseDao,
                       application: Application): ViewModel() {

    val database = dataSource

    private var exercise = MutableLiveData<Exercise?>()
    var lastWorkout = MutableLiveData<WorkoutInDb?>()

    val exerciseplan = database.getDefaultExercisePlan()

    init {
        initializeWorkoutList()
    }

    private fun initializeWorkoutList() {
        viewModelScope.launch {
            //lastWorkout.value = getLastWorkout()
            exercise.value = getWorkoutFromDatabase()
            printExercisePlan()

        }
    }
    private suspend fun getWorkoutFromDatabase(): Exercise? {
        var mWorkout = database.getLastExercise()
        // TODO(Legg inn sjekk om det er null verdi?)

        return mWorkout
    }
    private suspend fun getLastWorkout(): WorkoutInDb? {
        return database.getLast()
    }

    private suspend fun printExercisePlan() {
        Log.i("HistoryViewModel", "exercisePlan: ${database.getDefaultExercisePlan().value}")
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun saveWorkoutToDB() {
        viewModelScope.launch {
            val workoutInDb = WorkoutInDb()
            insert(workoutInDb)
            
        }
    }
    
    private suspend fun insert(workoutInDb: WorkoutInDb) {
        database.insert(workoutInDb)
    }
}