package com.example.nerdsexcercising.data.model

data class Workout (
    val name: String = "",
    val exercises: List<Exercise> = emptyList(),
    val reward: Int = 0
)