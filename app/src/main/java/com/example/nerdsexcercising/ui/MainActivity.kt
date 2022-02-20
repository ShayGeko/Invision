package com.example.nerdsexcercising.ui


import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
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

            }
            true
        }

//        For video here
//        setContentView(R.layout.ongoing_exercise)
//
//        val mediaController = MediaController(this)
//        mediaController.setAnchorView(findViewById<VideoView>(R.id.videoView))
//
//        val uri = Uri.parse("android.resource://$packageName/${R.raw.exercise_ongoing}")
//
//        findViewById<VideoView>(R.id.videoView).setMediaController(mediaController)
//        findViewById<VideoView>(R.id.videoView).setVideoURI(uri)
//        findViewById<VideoView>(R.id.videoView).requestFocus()
//        findViewById<VideoView>(R.id.videoView).start()

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}