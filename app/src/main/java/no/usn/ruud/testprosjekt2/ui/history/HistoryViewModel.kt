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

    private lateinit var _workout : MutableLiveData<List<WorkoutInDb>>

    val workout : MutableLiveData<List<WorkoutInDb>>
        get() = _workout

    init {
        initializeWorkoutList()
    }

    private fun initializeWorkoutList() {
        viewModelScope.launch {
            _workout.value = getWorkoutFromDatabase()
        }
    }

    private suspend fun getWorkoutFromDatabase(): List<WorkoutInDb> {
        Log.i("DATABASE", "${database.getAll()}")
        return database.getAll()
    }


}