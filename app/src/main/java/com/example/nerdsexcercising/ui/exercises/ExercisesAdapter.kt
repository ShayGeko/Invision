package com.example.nerdsexcercising.ui.exercises

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nerdsexcercising.R
import com.example.nerdsexcercising.data.model.Exercise
import com.google.android.material.button.MaterialButton

class ExercisesAdapter(private val exerciseList : ArrayList<Exercise>) : RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.exercises_item, parent, false);

        return ExerciseViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        var exercise : Exercise = exerciseList[position];
        holder.exerciseName.text = "${exercise.reps} ${exercise.name}"
        holder.btnStartExercise.setOnClickListener{
            Log.d("ExerciseAdapter", "starting exercise ${exercise.reps} ${exercise.name}")
        }
        Log.d("Adapter", exercise.toString())
        Log.d("Adapter", holder.toString())
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    class ExerciseViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val exerciseName : TextView = itemView.findViewById(R.id.exercise_name);
        val btnStartExercise : MaterialButton = itemView.findViewById(R.id.btn_start_exercise);
    }
}