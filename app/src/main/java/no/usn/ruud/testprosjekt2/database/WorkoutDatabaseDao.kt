package no.usn.ruud.testprosjekt2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WorkoutDatabaseDao {

    @Insert
    fun insert(workout: WorkoutInDb)

    @Update
    fun update(workout: WorkoutInDb)

    @Query("SELECT * FROM workout_table WHERE id = :key")
    fun get(key: Long): WorkoutInDb?

    @Query("DELETE FROM workout_table")
    fun clear()

    @Query("SELECT * FROM workout_table ORDER BY id ASC")
    fun getAll(): LiveData<List<WorkoutInDb>>

    @Query("SELECT * FROM workout_table ORDER BY id DESC LIMIT 1")
    fun getLast(): WorkoutInDb

}