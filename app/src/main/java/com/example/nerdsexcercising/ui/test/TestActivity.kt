package com.example.nerdsexcercising.ui.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.nerdsexcercising.R
import com.google.firebase.firestore.FirebaseFirestore

class TestActivity : AppCompatActivity() {
    private val TAG : String = "TestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // connections to firebase
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance();

        // test buttons
        val retrieveButton: Button = findViewById<Button>(R.id.main_btn_retrieveData)
        retrieveButton.setOnClickListener{
            val collected = firestore.collection("users");
            collected.get()
                .addOnSuccessListener { documents ->
                    for(document in documents) {
                        Log.d("SUCCESS", document.data.toString());
                    }
                }
        }

        val sendDataButton: Button = findViewById<Button>(R.id.main_btn_sendData)
        sendDataButton.setOnClickListener {
            val user = hashMapOf(
                "first" to "Alan",
                "middle" to "Mathison",
                "last" to "Turing",
                "born" to 1912
            )
            firestore.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }
}