package no.usn.ruud.testprosjekt2.ui.history

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import no.usn.ruud.testprosjekt2.database.WorkoutDatabaseDao
import no.usn.ruud.testprosjekt2.database.WorkoutInDb
import java.lang.reflect.Array.get

class HistoryViewModel(dataSource: WorkoutDatabaseDao,
                       application: Application): ViewModel() {

    //private lateinit var workout : MutableLiveData<List<WorkoutInDb>>
    val database = dataSource

    private var workout = MutableLiveData<WorkoutInDb?>()

    val nights = database.getAll()


    init {
        initializeWorkoutList()
        //Log.i("HistoryViewModel", "init: ${_workout.value?.get(0)?.toString()} ")
    }
    //var workout : MutableLiveData<List<WorkoutInDb>> = MutableLiveData<List<WorkoutInDb>>()


    private fun initializeWorkoutList() {
        viewModelScope.launch {
            Log.i("HistoryViewModel", "initWorkoutList kjørt")
            workout.value= getWorkoutFromDatabase()

            Log.i("HistoryViewModel", "_workout: ${workout.value.toString()}")
        }
    }

    private suspend fun getWorkoutFromDatabase(): WorkoutInDb? {
        var mWorkout = database.getLast()
        Log.i("HistoryViewModel", "$mWorkout lista: ${database.getAll()}")
        return mWorkout
    }


}