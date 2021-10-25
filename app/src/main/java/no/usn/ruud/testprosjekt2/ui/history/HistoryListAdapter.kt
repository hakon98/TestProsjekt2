package no.usn.ruud.testprosjekt2.ui.history

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import no.usn.ruud.testprosjekt2.R
import no.usn.ruud.testprosjekt2.database.WorkoutInDb

class HistoryListAdapter(private val context: Context, var workout: MutableLiveData<List<WorkoutInDb>>)
    : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>()
{

    private val mInflater: LayoutInflater
    private var mWorkout: WorkoutInDb? = null
    private var mWorkoutList: List<WorkoutInDb>?

    init {
        mInflater = LayoutInflater.from(context)
        mWorkoutList = workout.value //?: List<WorkoutInDb>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val myWorkoutItemLayout : Int = R.layout.history_list_item

        val myWorkoutItemView = mInflater.inflate(myWorkoutItemLayout, parent, false)

        return HistoryViewHolder(myWorkoutItemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        mWorkout = mWorkoutList?.get(position)
        Log.i("HistoryListAdapter",mWorkoutList.toString())
        // koble textview id med database var
        holder.dayOfWeek.text = mWorkout.toString()
        if (mWorkout != null) {
//            holder.date.text = mWorkout.date.toString()
//            holder.exerciseType.text = mWorkout.type
//            holder.exerciseReps.text = mWorkout.reps
//            holder.exerciseWeight.text = mWorkout.weight
        }
    }


    override fun getItemCount(): Int {
            return  mWorkoutList!!.size
    }






    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //private val workoutItemView: TextView = itemView.findViewById(R.id.textView)
        val dayOfWeek: TextView
        val date: TextView
        val exerciseType: TextView
        val exerciseReps: TextView
        val exerciseWeight: TextView

        init {
            dayOfWeek = itemView.findViewById<View>(R.id.text_day_of_week) as TextView
            date = itemView.findViewById<View>(R.id.text_date) as TextView
            exerciseType = itemView.findViewById<View>(R.id.text_exercise_type) as TextView
            exerciseReps = itemView.findViewById<View>(R.id.text_exercise_reps) as TextView
            exerciseWeight = itemView.findViewById<View>(R.id.text_exercise_weight) as TextView
        }
        /*
        fun bind(text: String?) {
            workoutItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): HistoryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.history_list_item, parent, false)
                return HistoryViewHolder(view)
            }
        }
        */
    }





}