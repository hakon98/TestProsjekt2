package no.usn.ruud.testprosjekt2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities =  [WorkoutInDb::class, Exercise::class] , version = 3, exportSchema = false)
abstract class FitGuuyDatabase : RoomDatabase() {

    abstract val workoutDatabaseDao: WorkoutDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: FitGuuyDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): FitGuuyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FitGuuyDatabase::class.java,
                        "fitGuuy_database"
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
                //workoutDatabaseDao.deleteAll()
                //Legg til eksempler på ord
                var word = WorkoutInDb( 0, 12, "", "", "")
                workoutDatabaseDao.insert( word)
                word =  WorkoutInDb( 1,  1234, "", "")
                workoutDatabaseDao.insert( word)
                word = WorkoutInDb()
                word.type1 = "Benkpress"
                workoutDatabaseDao.insert(word)
                word = WorkoutInDb()
                word.type2 = "Knebøy"
                workoutDatabaseDao.insert(word)
                word = WorkoutInDb()
                word.type3 = "Markløft"
                workoutDatabaseDao.insert(word)
            }

        }

}