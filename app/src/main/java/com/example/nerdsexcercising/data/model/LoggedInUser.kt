package com.example.nerdsexcercising.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String,
    val experience: Double,
    val progress: List<Workout>,
    val selectedWorkout: Workout?,
    var cntCompletedExercises: Number,
    var cntCompletedWorkouts: Number,
)