package no.usn.ruud.testprosjekt2.ui.history

import android.app.ActivityOptions
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment

import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import no.usn.ruud.testprosjekt2.R
import no.usn.ruud.testprosjekt2.database.WorkoutInDb
import no.usn.ruud.testprosjekt2.databinding.HistoryListItemBinding

class HistoryListAdapter: ListAdapter<WorkoutInDb, HistoryListAdapter.HistoryViewHolder>(WorkoutInDbDiffCallback())
{
    class WorkoutInDbDiffCallback : DiffUtil.ItemCallback<WorkoutInDb>() {
        override fun areItemsTheSame(oldItem: WorkoutInDb, newItem: WorkoutInDb): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WorkoutInDb, newItem: WorkoutInDb): Boolean {
            return oldItem == newItem
        }

    }
    /*
    //private val mInflater: LayoutInflater
    //private var mWorkout: WorkoutInDb? = null
    //private var mWorkoutList: List<WorkoutInDb?>?

    init {
        mInflater = LayoutInflater.from(context)
        //mWorkoutList = workout.value //?: List<WorkoutInDb>()
        //Log.i("HistoryListAdapter", "init: ${ mWorkoutList.toString() }")
    }

     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        /*
        val myWorkoutItemLayout : Int = R.layout.history_list_item
        val myWorkoutItemView = mInflater.inflate(myWorkoutItemLayout, parent, false)
        return HistoryViewHolder(myWorkoutItemView)
         */
        return HistoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holderHistory: HistoryViewHolder, position: Int) {
        //Log.i("HistoryListAdapter",workout.value.toString())

        holderHistory.itemView.setOnClickListener {

            val navController = R.id.nav_host_fragment_activity_main

            holderHistory.itemView.transitionName = holderHistory.itemId.toString()

            val extras = FragmentNavigatorExtras(holderHistory.itemView to  "transformation_text_day_of_the_week")

            findNavController(holderHistory.itemView.findFragment()).navigate(
                R.id.navigation_history_info, null, null, extras
            )

        }

        val mWorkout = getItem(position)
        Log.i("HistoryListAdapter",mWorkout.toString())
        // koble textview id med database var
        holderHistory.dayOfWeek.text = mWorkout.toString()
        if (mWorkout != null) {
            holderHistory.date.text = mWorkout!!.date.toString()
            holderHistory.exerciseType.text = mWorkout!!.type1
            holderHistory.exerciseReps.text = mWorkout!!.reps1
            holderHistory.exerciseWeight.text = mWorkout!!.weight1
        }


    }

    /*
    override fun getItemCount(): Int {
            return  workout.value?.size ?: 0
    }

    fun getHistoryListData(workout: MutableLiveData<List<WorkoutInDb>>) {
        this.workout = workout
        Log.i("HistoryListAdapter", "getHistoryList: ${workout.value?.get(1)?.toString()}")
    }

     */

    class HistoryViewHolder(binding: HistoryListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        //private val workoutItemView: TextView = itemView.findViewById(R.id.textView)
        val dayOfWeek: TextView
        val date: TextView
        val exerciseType: TextView
        val exerciseReps: TextView
        val exerciseWeight: TextView

        init {
            dayOfWeek = binding.textDayOfWeek
            date = binding.textDate
            exerciseType = binding.textExerciseType
            exerciseReps = binding.textExerciseReps
            exerciseWeight = binding.textExerciseWeight
        }
        companion object {
            fun from(parent: ViewGroup): HistoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HistoryListItemBinding.inflate(layoutInflater, parent, false)

                return HistoryViewHolder(binding)
            }
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