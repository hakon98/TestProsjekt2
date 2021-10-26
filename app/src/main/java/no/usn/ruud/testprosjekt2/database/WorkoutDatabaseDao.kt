package no.usn.ruud.testprosjekt2.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.OnConflictStrategy

@Dao
interface WorkoutDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(workout: WorkoutInDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: Exercise)

    @Update
    suspend fun update(workout: WorkoutInDb)

    @Query("SELECT * FROM workout_history_table WHERE id = :key")
    suspend fun get(key: Long): WorkoutInDb?


    @Query("SELECT * FROM workout_history_table WHERE id = :key")
    suspend fun getID(key: Long): WorkoutInDb?
    
    @Query("DELETE FROM sqlite_sequence where name='workout_history_table'")
    suspend fun clearWTab()
    @Query("DELETE FROM sqlite_sequence where name='exercise_plan_table'")
    suspend fun clearETab()

    @Query("SELECT * FROM workout_history_table ORDER BY id ASC")
    fun getAll():LiveData<List<WorkoutInDb>>

    @Query("SELECT * FROM exercise_plan_table WHERE id = :key")
    fun getExerciseplan(key: String):LiveData<List<Exercise>>

    @Query("SELECT * FROM workout_history_table ORDER BY id DESC LIMIT 1")
    suspend fun getLast(): WorkoutInDb

    @Query("SELECT * FROM exercise_plan_table ORDER BY id DESC LIMIT 1")
    suspend fun getLastExercise(): Exercise

    @Query("DELETE FROM workout_history_table")
    suspend fun deleteAllWor()
    @Query("DELETE FROM exercise_plan_table")
    suspend fun deleteAllEx()

    @Query("SELECT * FROM exercise_plan_table WHERE part_of_name='default'" )
    fun getDefaultExercisePlan(): LiveData<List<Exercise>?>

    
//    fun getWorkout(): List<WorkoutInDb>{
//        
//    }

}