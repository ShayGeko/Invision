package com.example.nerdsexcercising

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // connections to firebase
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance();

        // test buttons
        val retrieveButton: Button = findViewById<Button>(R.id.main_btn_retrieveData);
        retrieveButton.setOnClickListener{
            val collected = firestore.collection("test")
                .get()
        }

        val sendDataButton: Button = findViewById<Button>(R.id.main_btn_sendData);
        sendDataButton.setOnClickListener {

        }
    }
}