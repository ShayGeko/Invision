package com.example.nerdsexcercising.ui.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.nerdsexcercising.R
import com.example.nerdsexcercising.data.Repository
import com.example.nerdsexcercising.data.model.Exercise
import com.example.nerdsexcercising.data.model.Workout

class TestActivity : AppCompatActivity() {
    private val TAG : String = "TestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val repository = Repository()

        // test buttons
        val retrieveButton: Button = findViewById<Button>(R.id.main_btn_retrieveData)
        retrieveButton.setOnClickListener{
            repository.getWorkouts()
                .addOnSuccessListener { documents ->
                    for(document in documents) {
                        Log.d("SUCCESS", document.data.toString());
                    }
                }
        }

        val sendDataButton: Button = findViewById<Button>(R.id.main_btn_sendData)
        sendDataButton.setOnClickListener {
            val squats20 = Exercise("squats", 20);
            val pushups25 = Exercise("pushups", 25);
            val situps50 = Exercise("situps", 50);
            val pullups30 = Exercise("pullups", 30);
            val workout = Workout(
                "Cardio",
                listOf(squats20, pushups25,  situps50, pullups30),
                3000
            )
            repository.addWorkout(workout)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }
}