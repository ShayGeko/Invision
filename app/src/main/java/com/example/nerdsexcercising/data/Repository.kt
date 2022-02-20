package com.example.nerdsexcercising.data

import com.example.nerdsexcercising.data.model.Workout
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

object Repository {
        private val TAG : String = "Repository"
        private val db : FirebaseFirestore = FirebaseFirestore.getInstance()

        fun getWorkouts () : Task<QuerySnapshot> {
                return db.collection("workouts").get();
        }
        fun addWorkout (workout : Workout) : Task<DocumentReference>{
                return db.collection("workouts").add(workout);
        }
}