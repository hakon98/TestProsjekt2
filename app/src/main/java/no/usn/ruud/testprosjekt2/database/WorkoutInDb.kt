package no.usn.ruud.testprosjekt2.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_table")
data class WorkoutInDb (

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "date yeah")
    val date: Long = 0,
    @ColumnInfo(name = "1_exercise_type")
    val type: String = "",
    @ColumnInfo(name = "1_exercise_reps")
    val reps: String = "",
    @ColumnInfo(name = "1_exercise_kg")
    val kg: String = ""

)
