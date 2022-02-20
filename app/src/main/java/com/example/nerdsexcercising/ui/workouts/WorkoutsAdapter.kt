package com.example.nerdsexcercising.ui.workouts

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nerdsexcercising.R
import com.example.nerdsexcercising.data.model.Exercise
import com.example.nerdsexcercising.data.model.Workout

class WorkoutsAdapter(private val workoutList : ArrayList<Workout>) : RecyclerView.Adapter<WorkoutsAdapter.ExerciseViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.workouts_item, parent, false);

        return ExerciseViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        var workout : Workout = workoutList[position];
        holder.workoutName.text = "${workout.name} ${workout.reward}"
        Log.d("Adapter", workout.toString())
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }

    class ExerciseViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val workoutName : TextView = itemView.findViewById(R.id.workout_name);
    }
}