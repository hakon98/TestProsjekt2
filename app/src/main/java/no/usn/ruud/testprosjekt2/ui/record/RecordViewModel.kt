package no.usn.ruud.testprosjekt2.ui.record

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import no.usn.ruud.testprosjekt2.database.WorkoutDatabaseDao
import no.usn.ruud.testprosjekt2.database.WorkoutInDb

class RecordViewModel (val database: WorkoutDatabaseDao,
                       application: Application
): AndroidViewModel(application) {



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