package no.usn.ruud.testprosjekt2.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import no.usn.ruud.testprosjekt2.database.WorkoutDatabaseDao
import no.usn.ruud.testprosjekt2.database.WorkoutInDb

class HistoryViewModel(val database: WorkoutDatabaseDao,
                       application: Application): AndroidViewModel(application) {

    private var workout = MutableLiveData<WorkoutInDb?>()

    init {
        initializeWorkout()
    }

    private fun initializeWorkout() {
        viewModelScope.launch {
            workout.value = getWorkoutFromDatabase()
        }
    }

    private suspend fun getWorkoutFromDatabase(): WorkoutInDb? {
        return database.getLast()
    }

}