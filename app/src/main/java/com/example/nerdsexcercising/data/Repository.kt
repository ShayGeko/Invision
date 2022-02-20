package com.example.nerdsexcercising.data

import com.example.nerdsexcercising.data.model.LoggedInUser
import com.example.nerdsexcercising.data.model.Workout
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*

object Repository {
        private val TAG : String = "Repository"
        private val db : FirebaseFirestore = FirebaseFirestore.getInstance()

        fun getWorkouts () : Task<QuerySnapshot> {
                return db.collection("workouts").get();
        }
        fun addWorkout (workout : Workout) : Task<DocumentReference>{
                return db.collection("workouts").add(workout);
        }
        fun updateUser(user : LoggedInUser) : Task<Void> {
                return db.collection("users").document(user.userId).set(user);
        }
        fun getUser(uid : String): Task<DocumentSnapshot> {
                return db.collection("users").document(uid).get()
        }
}