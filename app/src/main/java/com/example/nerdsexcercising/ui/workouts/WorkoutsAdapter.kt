package com.example.nerdsexcercising.ui.workouts

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nerdsexcercising.R
import com.example.nerdsexcercising.data.model.Exercise
import com.example.nerdsexcercising.data.model.Workout
import com.example.nerdsexcercising.ui.exercises.ExercisesActivity

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
        holder.workoutName.text = "${workout.name}"
        holder.workoutReward.text = "${workout.reward} exp"
        Log.d("Adapter", workout.toString())


        holder.itemView.setOnClickListener{
            val openWorkoutIntent = Intent(it.context, ExercisesActivity::class.java)
            openWorkoutIntent.putExtra("workoutId", holder.workoutName.text)

            it.context.startActivity(openWorkoutIntent)
        }
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }

    class ExerciseViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val workoutName : TextView = itemView.findViewById(R.id.workout_name);
        val workoutReward : TextView = itemView.findViewById(R.id.workout_reward);
        override fun onClick(v: View) {
            Log.d("WorkoutAdapter", v.toString());
            val openWorkoutIntent = Intent(v.context, ExercisesActivity::class.java)
            openWorkoutIntent.putExtra("workoutId", workoutName.text)

            v.context.startActivity(openWorkoutIntent)
        }


    }
}