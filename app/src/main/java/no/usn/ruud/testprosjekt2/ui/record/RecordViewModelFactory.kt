package no.usn.ruud.testprosjekt2.ui.record

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import no.usn.ruud.testprosjekt2.database.WorkoutDatabaseDao
import no.usn.ruud.testprosjekt2.ui.history.HistoryViewModel
import java.lang.IllegalArgumentException
import javax.sql.CommonDataSource

class RecordViewModelFactory(
    private val dataSource: WorkoutDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RecordViewModel::class.java)) {
            return RecordViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}