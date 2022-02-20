package com.example.nerdsexcercising.data

import kotlin.math.pow

object Utility {
    fun getLevel(_exp: Number) = _exp.toDouble().pow(1/2.5)
    fun getExperienceRequired(_level: Number) = _level.toDouble().pow(2.5)
}