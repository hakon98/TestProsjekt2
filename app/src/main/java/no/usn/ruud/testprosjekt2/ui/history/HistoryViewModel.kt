package no.usn.ruud.testprosjekt2.ui.history

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import no.usn.ruud.testprosjekt2.database.WorkoutDatabaseDao
import no.usn.ruud.testprosjekt2.database.WorkoutInDb

class HistoryViewModel(val database: WorkoutDatabaseDao,
                       application: Application): AndroidViewModel(application) {

    private var _workout : MutableLiveData<List<WorkoutInDb>> = MutableLiveData<List<WorkoutInDb>>()


    init {
        initializeWorkoutList()
        Log.i("HistoryViewModel", "init: ${_workout.value?.get(0)?.toString()} ")
    }
    var workout : MutableLiveData<List<WorkoutInDb>> = _workout


    private fun initializeWorkoutList() {
        viewModelScope.launch {
            Log.i("HistoryViewModel", "initWorkoutList kjørt")
            _workout.setValue(getWorkoutFromDatabase())

            Log.i("HistoryViewModel", "_workout: ${_workout.value.toString()}")
        }
    }

    private suspend fun getWorkoutFromDatabase(): List<WorkoutInDb> {
        Log.i("HistoryViewModel", "${database.getAll()}")
        return database.getAll()
    }


}