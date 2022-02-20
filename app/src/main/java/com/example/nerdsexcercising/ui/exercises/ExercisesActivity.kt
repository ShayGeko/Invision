package com.example.nerdsexcercising.ui.exercises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nerdsexcercising.R
import com.example.nerdsexcercising.data.model.Exercise
import com.example.nerdsexcercising.data.model.Workout
import com.google.firebase.firestore.FirebaseFirestore

class ExercisesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseList: ArrayList<Exercise>
    private lateinit var exerciseAdapter: ExercisesAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        val goback = findViewById<TextView>(R.id.singleEx_textView_goBack)

        goback.setOnClickListener{
            finish()
        }

        recyclerView = findViewById(R.id.exercises_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true);

        exerciseList = arrayListOf();

        exerciseAdapter = ExercisesAdapter(exerciseList)

        recyclerView.adapter = exerciseAdapter

        eventChangeListener()
    }

    private fun eventChangeListener(){

        db = FirebaseFirestore.getInstance()
        db.collection("workouts").document("QlPRiJOE9KX7yQKJtNOd")
            .get()
            .addOnSuccessListener {document ->
                Log.d("ExerciseFragment", document.toString());
                val workout = document.toObject(Workout::class.java)
                if (workout != null) {
                    for(ex : Exercise in workout.exercises){
                        exerciseList.add(ex)
                    }

                    exerciseAdapter.notifyDataSetChanged();
                }
            }
    }
}