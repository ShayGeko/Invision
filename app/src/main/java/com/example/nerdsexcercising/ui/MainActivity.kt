package com.example.nerdsexcercising.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.nerdsexcercising.R
import com.example.nerdsexcercising.ui.challenges.ChallengesFragment
import com.example.nerdsexcercising.ui.home.HomeFragment
import com.example.nerdsexcercising.ui.exercises.ExerciseFragment
import com.example.nerdsexcercising.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    private lateinit var bottomNavigationView: BottomNavigationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide();
        auth = Firebase.auth;

        val exerciseFragment: Fragment = ExerciseFragment()
        val homeFragment: Fragment = HomeFragment()
        // val challengesFragment: Fragment = ChallengesFragment()
        val profileFragment: Fragment = ProfileFragment()

        setCurrentFragment(homeFragment)

        bottomNavigationView= findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.main_botNavBtn_exercise->setCurrentFragment(exerciseFragment)
                R.id.main_botNavBtn_home->setCurrentFragment(homeFragment)
                // R.id.main_botNavBtn_challenges->setCurrentFragment(challengesFragment)
                R.id.main_botNavBtn_profile->setCurrentFragment(profileFragment)
            }
            true
        }
        bottomNavigationView.menu.getItem(1).isChecked = true;
    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager
            .beginTransaction().apply {
                // replace current frameLayout (aka main_frag_showingFrag) with selected Fragment
                replace(R.id.main_frag_showingFrag,fragment);
                commit()
            }
}