package no.usn.ruud.testprosjekt2.ui.record

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import no.usn.ruud.testprosjekt2.database.WorkoutDatabaseDao

class RecordViewModel (val database: WorkoutDatabaseDao,
                       application: Application
): AndroidViewModel(application) {



    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}