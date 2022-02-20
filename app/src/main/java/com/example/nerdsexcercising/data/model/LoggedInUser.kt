package com.example.nerdsexcercising.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String,
    val level: Number,
    val progress: List<Workout>,
    var selectedWorkout: Workout?,
    var cntCompletedExercises: Number,
    var cntCompletedWorkouts: Number
)