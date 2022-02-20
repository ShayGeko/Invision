package com.example.nerdsexcercising.data.model

data class Exercise (
    val name: String = "",
    val reps: Int = 0,
    var isCompleted: Boolean = false
)