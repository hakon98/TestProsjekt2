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

    @Update
    suspend fun update(workout: WorkoutInDb)

    @Query("SELECT * FROM workout_table WHERE id = :key")
    suspend fun get(key: Long): WorkoutInDb?


    @Query("SELECT * FROM workout_table WHERE id = :key")
    suspend fun getID(key: Long): WorkoutInDb?
    
    @Query("DELETE FROM workout_table")
    suspend fun clear()

    @Query("SELECT * FROM workout_table ORDER BY id ASC")
    fun getAll():LiveData<List<WorkoutInDb>>

    @Query("SELECT * FROM workout_table ORDER BY id DESC LIMIT 1")
    suspend fun getLast(): WorkoutInDb

    @Query("DELETE FROM workout_table")
    suspend fun deleteAll()

    
//    fun getWorkout(): List<WorkoutInDb>{
//        
//    }

}