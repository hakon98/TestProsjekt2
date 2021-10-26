package no.usn.ruud.testprosjekt2.ui.record

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import no.usn.ruud.testprosjekt2.database.Exercise
import no.usn.ruud.testprosjekt2.database.WorkoutInDb
import no.usn.ruud.testprosjekt2.databinding.HistoryListItemBinding
import no.usn.ruud.testprosjekt2.databinding.RecordListItemBinding
import no.usn.ruud.testprosjekt2.ui.history.HistoryListAdapter

class RecordListAdapter: ListAdapter<Exercise, RecordListAdapter.RecordViewHolder>(WorkoutInDbDiffCallback()) {

    var lastWorkout: WorkoutInDb? = WorkoutInDb()

    class WorkoutInDbDiffCallback : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordViewHolder {
        return RecordViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val mWorkout = getItem(position)
        Log.i("RecordListAdapter",lastWorkout.toString())
        holder.lastWeight.text = lastWorkout!!.weight1
        holder.name.text = mWorkout.name
    }
    
    class RecordViewHolder(binding: RecordListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val lastWeight: TextView
        val name: TextView


        init {
            lastWeight = binding.txtLastWeight
            name = binding.txtExerciseName

        }

        companion object {
            fun from(parent: ViewGroup): RecordViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecordListItemBinding.inflate(layoutInflater, parent, false)
                return RecordViewHolder(binding)
            }
        }
    }


}