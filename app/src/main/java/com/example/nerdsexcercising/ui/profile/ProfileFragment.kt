package com.example.nerdsexcercising.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nerdsexcercising.R
import com.example.nerdsexcercising.data.Repository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var user = Repository.getCacheUser();
        Firebase.auth.currentUser?.let {
            GlobalScope.launch {
                if (user === null) {
                    user = Repository.getUser(it);
                    this@ProfileFragment.activity?.runOnUiThread {
                        // name
                        val nameTV: TextView = view.findViewById(R.id.profile_textView_name);
                        nameTV.text = user!!.displayName;

                        // completed achievements
                        val woTV: TextView = view.findViewById(R.id.profile_textView_completedWO);
                        val cTV: TextView = view.findViewById(R.id.profile_textView_completedC);

                        woTV.text = user!!.cntCompletedExercises.toString();
                        cTV.text = user!!.cntCompletedWorkouts.toString();
                    }
                }
            }
        }

        // name
        val nameTV: TextView = view.findViewById(R.id.profile_textView_name);
        nameTV.text = user?.displayName ?: "null";

        // completed achievements
        val woTV: TextView = view.findViewById(R.id.profile_textView_completedWO);
        val cTV: TextView = view.findViewById(R.id.profile_textView_completedC);

        woTV.text = user?.cntCompletedExercises.toString() ?: "-1";
        cTV.text = user?.cntCompletedWorkouts.toString() ?: "-1";
    }
}