package no.usn.ruud.testprosjekt2.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.firebase.ui.auth.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [WorkoutInDb::class], version = 1, exportSchema = false)
abstract class WorkoutDatabase : RoomDatabase() {

    abstract val workoutDatabaseDao: WorkoutDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: WorkoutDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): WorkoutDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WorkoutDatabase::class.java,
                        "sleep_history_database"
                    )
                        .addCallback(WorkoutDatabaseCallback(scope))
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }




        }

    }


    private class WorkoutDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.workoutDatabaseDao)
                    }
                }
            }

            suspend fun populateDatabase(workoutDatabaseDao: WorkoutDatabaseDao) {
                // Delete all content here
                //workoutDatabaseDao.deleteAll()
                workoutDatabaseDao.deleteAll()
                //Legg til eksempler på ord
                var word = WorkoutInDb( 0, 12, "", "", "")
                workoutDatabaseDao.insert( word)
                word =  WorkoutInDb( 1,  1234, "", "")
                workoutDatabaseDao.insert( word)
                word = WorkoutInDb()
                word.type = "Benkpress"
                workoutDatabaseDao.insert(word)
                word = WorkoutInDb()
                word.type = "Knebøy"
                workoutDatabaseDao.insert(word)
                word = WorkoutInDb()
                word.type = "Markløft"
                workoutDatabaseDao.insert(word)
            }

        }

}