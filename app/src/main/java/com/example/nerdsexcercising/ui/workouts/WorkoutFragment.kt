package com.example.nerdsexcercising.ui.workouts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nerdsexcercising.R
import com.example.nerdsexcercising.data.model.Workout
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutsList: ArrayList<Workout>
    private lateinit var workoutAdapter: WorkoutsAdapter
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recyclerView = itemView.findViewById(R.id.workouts_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true);

        workoutsList = arrayListOf();

        workoutAdapter = WorkoutsAdapter(workoutsList)

        recyclerView.adapter = workoutAdapter

        eventChangeListener()

    }

    private fun eventChangeListener(){

        db = FirebaseFirestore.getInstance()
        db.collection("workouts")
            .get()
            .addOnSuccessListener {documents ->
                for (document in documents) {
                    Log.d("ExerciseFragment", document.toString());
                    val workout = document.toObject(Workout::class.java)
                    if (workout != null) {
                        workoutsList.add(workout)
                        workoutAdapter.notifyDataSetChanged();
                    }
                }
            }
    }
}