package com.example.nerdsexcercising.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String = "",
    val displayName: String ="",
    val level: Int = 0,
    val progress: List<Workout> = emptyList(),
    var selectedWorkout: Workout? = null,
    var cntCompletedExercises: Int = 0,
    var cntCompletedWorkouts: Int = 0
)