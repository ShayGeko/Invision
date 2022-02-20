package com.example.nerdsexcercising.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nerdsexcercising.R
import com.example.nerdsexcercising.ui.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.widget.TextView
import com.example.nerdsexcercising.data.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private lateinit var auth : FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logoutBtn = view.findViewById<MaterialButton>(R.id.logout_btn);

        logoutBtn.setOnClickListener {
            logout(view)
        }


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
    fun logout(view: View){
        Log.d("ProfileFragment", "Logout");

        auth.signOut();
        GoogleSignIn.getClient(
            requireContext(),
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        ).signOut().addOnCompleteListener {
            Log.d("LOGOUT", "logged out successfully")
            val logoutIntent = Intent(view.context, LoginActivity::class.java);
            logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutIntent)
        }
    }
}