package com.example.nerdsexcercising.data

import android.util.Log
import com.example.nerdsexcercising.data.model.LoggedInUser
import com.example.nerdsexcercising.data.model.Workout
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

object Repository {
        private val TAG : String = "Repository"
        private val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        private var cachedUser: LoggedInUser? = null;

        fun getWorkouts () : Task<QuerySnapshot> {
                return db.collection("workouts").get();
        }
        fun addWorkout (workout : Workout) : Task<DocumentReference>{
                return db.collection("workouts").add(workout);
        }
        fun getGuestUser(id: String): LoggedInUser = LoggedInUser(
                id,
                "Guest",
                0.0,
                emptyList(),
                null,
                0,
                0
        );
        fun updateUser(user : LoggedInUser) : Task<Void> {
                return db.collection("users").document(user.userId).set(user);
        }
        fun getCacheUser(): LoggedInUser? = cachedUser;
        suspend fun getUser(uid : String): LoggedInUser {
                val collected: Task<DocumentSnapshot> =
                        db.collection("users").document(uid).get();
                val awaited = suspendCancellableCoroutine<DocumentSnapshot> { continuation ->
                        collected.addOnSuccessListener {
                                continuation.resume(it) {};
                        }
                        collected.addOnFailureListener() {
                                continuation.resumeWithException(it);
                        }
                }
                val data = awaited.data;
                Log.d("data", data.toString());

                cachedUser = if (data === null) {
                        val guest = getGuestUser(uid);
                        updateUser(guest)
                        guest;
                } else {
                        LoggedInUser(
                                data["userId"] as String,
                                data["displayName"] as String,
                                (data["experience"] as Long).toDouble(),
                                data["progress"] as List<Workout>,
                                data["selectedWorkout"] as Workout?,
                                (data["cntCompletedExercises"] as Long).toInt(),
                                (data["cntCompletedWorkouts"] as Long).toInt(),
                        )
                }
                return cachedUser!!;
        }
}