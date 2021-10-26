package no.usn.ruud.testprosjekt2

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import no.usn.ruud.testprosjekt2.database.FitGuuyDatabase
import no.usn.ruud.testprosjekt2.database.WorkoutDatabaseDao
import no.usn.ruud.testprosjekt2.database.WorkoutInDb
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FitGuuyDatabaseTest {

    private lateinit var workoutDatabaseDao: WorkoutDatabaseDao
    private lateinit var db : FitGuuyDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, FitGuuyDatabase::class.java).
        allowMainThreadQueries().build()
        workoutDatabaseDao = db.workoutDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun insertWorkout() {

        val workout = WorkoutInDb()
        workoutDatabaseDao.insert(workout)
        val lastWorkout = workoutDatabaseDao.getLast()

    }



}