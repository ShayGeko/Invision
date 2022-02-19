package com.example.nerdsexcercising.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.nerdsexcercising.R
import com.example.nerdsexcercising.ui.challenges.ChallengesFragment
import com.example.nerdsexcercising.ui.home.HomeFragment
import com.example.nerdsexcercising.ui.exercices.ExerciseFragment
import com.example.nerdsexcercising.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val exerciseFragment=ExerciseFragment()
        val homeFragment=HomeFragment()
        val challengesFragment=ChallengesFragment()
        val profileFragment=ProfileFragment()

        setCurrentFragment(homeFragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.exercise->setCurrentFragment(exerciseFragment)
                R.id.home->setCurrentFragment(homeFragment)
                R.id.challenges->setCurrentFragment(challengesFragment)
                R.id.profile->setCurrentFragment(profileFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}