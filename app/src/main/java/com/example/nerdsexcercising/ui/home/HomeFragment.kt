package com.example.nerdsexcercising.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.nerdsexcercising.R
import com.example.nerdsexcercising.data.Repository
import com.example.nerdsexcercising.data.Utility
import com.example.nerdsexcercising.data.model.LoggedInUser
import com.example.nerdsexcercising.data.model.Workout
import kotlin.math.floor
import kotlin.math.pow

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = LoggedInUser(
            "Ike",
            "Ike",
            10,
            listOf(),
            Workout("SelectedWO", listOf(), 0),
            0,
            0,
        );

        // "hello x, let's exercise!"
        val helloTextView: TextView =
            view.findViewById<TextView>(R.id.homeFragment_textView_hello);
        helloTextView.text = "Hey " + user.displayName + "\nLet's Exercise!";

        // level bar
        val level: Double = Utility.getLevel(user.experience);
        val experienceNow: Number = user.experience;
        val experienceGoal: Number = (level + 1).pow(2.5);
        val levelProgressBar: ProgressBar =
            view.findViewById<ProgressBar>(R.id.homeFragment_progressBar_);
        levelProgressBar.progress =
            floor(
                (experienceGoal.toDouble() - experienceNow.toDouble())
                        / experienceGoal.toDouble()).toInt();

        // "current progress"
        val currentProgressTextView: TextView =
            view.findViewById<TextView>(R.id.homefragment_textView_currentProgress);

        // add/resume exercise
        val addresumeExerciseBtn: Button =
            view.findViewById<Button>(R.id.homeFragment_btn_addresumeExercise);
        addresumeExerciseBtn.setOnClickListener {
            // transition to exercise fragment
        }
    }
}