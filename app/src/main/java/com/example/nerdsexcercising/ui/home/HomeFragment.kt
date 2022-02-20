package com.example.nerdsexcercising.ui.home

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.nerdsexcercising.R
import com.example.nerdsexcercising.data.Repository
import com.example.nerdsexcercising.data.Utility
import com.example.nerdsexcercising.data.model.LoggedInUser
import com.example.nerdsexcercising.data.model.Workout
import com.example.nerdsexcercising.ui.exercises.ExerciseFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.ceil
import kotlin.math.floor

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var user = Repository.getCacheUser();
        Firebase.auth.currentUser?.let {
            GlobalScope.launch {
                if (user === null) {
                    user = Repository.getUser(it);

                    this@HomeFragment.activity?.runOnUiThread {
                        // "hello x, let's exercise!"
                        val helloTextView: TextView =
                            view.findViewById<TextView>(R.id.homeFragment_textView_hello);
                        helloTextView.text = "Hey " + user?.displayName;

                        // level progress bar
                        var experience: Double = user?.experience ?: 0.0;
                        val level: Double = Utility.getLevel(experience);
                        val experienceNow: Int =
                            floor(experience - Utility.getExperienceRequired(level - 1)).toInt();
                        val experienceGoal: Int =
                            ceil(Utility.getExperienceRequired(level + 1) - experience).toInt();
                        val levelProgressBar: ProgressBar =
                            view.findViewById<ProgressBar>(R.id.homeFragment_progressBar_);
                        levelProgressBar.progress =
                            ((experienceNow / experienceGoal.toDouble()) * 100).toInt();

                        // level text
                        val levelTextView: TextView = view.findViewById(R.id.homeFragment_textView_level);
                        levelTextView.text = (level+1).toInt().toString();

                        // current exp text
                        val currentEXPTextView: TextView = view.findViewById(R.id.homeFragment_textView_currentEXP);
                        val maxEXPTextView: TextView = view.findViewById(R.id.homeFragment_textView_maxEXP);
                        currentEXPTextView.text = experienceNow.toString();
                        maxEXPTextView.text = getString(R.string.total_level_xp)
                            .replace("1500", experienceGoal.toString());

                        // your workout, comments, and exp
                        val yourWorkoutCommentTextView: TextView =
                            view.findViewById(R.id.homeFragment_textView_yourWorkoutComment)
                        val yourWorkoutExpTextView: TextView=
                            view.findViewById(R.id.homeFragment_textView_yourWorkoutEXP);
                        val currentWO: Workout? = user?.selectedWorkout;
                        if (currentWO === null) {
                            yourWorkoutCommentTextView.text = getString(R.string.curr_workout_section_none);
                            yourWorkoutExpTextView.visibility = View.GONE;
                        }
                        else {
                            yourWorkoutCommentTextView.textSize =
                                (yourWorkoutCommentTextView.textSize * 1.2).toFloat();
                            yourWorkoutCommentTextView.setTypeface(null, Typeface.BOLD);
                            yourWorkoutCommentTextView.text = currentWO.name;
                            yourWorkoutExpTextView.visibility = View.VISIBLE;
                            yourWorkoutExpTextView.text = currentWO.reward.toString() + " EXP";
                        }

                        // add/resume exercise
                        val addresumeExerciseBtn: ImageView =
                            view.findViewById<ImageView>(R.id.homeFragment_btn_addresumeExercise);
                        addresumeExerciseBtn.setOnClickListener {
                            // transition to exercise fragment
                            activity?.supportFragmentManager
                                ?.beginTransaction()
                                ?.apply {
                                    replace(R.id.main_frag_showingFrag, ExerciseFragment())
                                    commit()
                                }
                        }
                    }
                }
            }
        };

        // "hello x, let's exercise!"
        val helloTextView: TextView =
            view.findViewById<TextView>(R.id.homeFragment_textView_hello);
        helloTextView.text = "Hey " + user?.displayName;

        // level progress bar
        var experience: Double = user?.experience ?: 0.0;
        val level: Double = Utility.getLevel(experience);
        val experienceNow: Int =
            floor(experience - Utility.getExperienceRequired(level - 1)).toInt();
        val experienceGoal: Int =
            ceil(Utility.getExperienceRequired(level + 1) - experience).toInt();
        val levelProgressBar: ProgressBar =
            view.findViewById<ProgressBar>(R.id.homeFragment_progressBar_);
        levelProgressBar.progress =
            ((experienceNow / experienceGoal.toDouble()) * 100).toInt();

        // level text
        val levelTextView: TextView = view.findViewById(R.id.homeFragment_textView_level);
        levelTextView.text = (level+1).toInt().toString();

        // current exp text
        val currentEXPTextView: TextView = view.findViewById(R.id.homeFragment_textView_currentEXP);
        val maxEXPTextView: TextView = view.findViewById(R.id.homeFragment_textView_maxEXP);
        currentEXPTextView.text = experienceNow.toString();
        maxEXPTextView.text = getString(R.string.total_level_xp)
            .replace("1500", experienceGoal.toString());

        // your workout, comments, and exp
        val yourWorkoutCommentTextView: TextView =
            view.findViewById(R.id.homeFragment_textView_yourWorkoutComment)
        val yourWorkoutExpTextView: TextView=
            view.findViewById(R.id.homeFragment_textView_yourWorkoutEXP);
        val currentWO: Workout? = user?.selectedWorkout;
        if (currentWO === null) {
            yourWorkoutCommentTextView.text = getString(R.string.curr_workout_section_none);
            yourWorkoutExpTextView.visibility = View.GONE;
        }
        else {
            yourWorkoutCommentTextView.textSize =
                (yourWorkoutCommentTextView.textSize * 1.2).toFloat();
            yourWorkoutCommentTextView.setTypeface(null, Typeface.BOLD);
            yourWorkoutCommentTextView.text = currentWO.name;
            yourWorkoutExpTextView.visibility = View.VISIBLE;
            yourWorkoutExpTextView.text = currentWO.reward.toString() + " EXP";
        }

        // add/resume exercise
        val addresumeExerciseBtn: ImageView =
            view.findViewById<ImageView>(R.id.homeFragment_btn_addresumeExercise);
        addresumeExerciseBtn.setOnClickListener {
            // transition to exercise fragment
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.apply {
                    replace(R.id.main_frag_showingFrag, ExerciseFragment())
                    commit()
                }
        }
    }
}