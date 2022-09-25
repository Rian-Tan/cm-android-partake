package com.partake.partake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class addstuff : AppCompatActivity() {
    val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addstuff)
        val submitbutton: Button = findViewById(R.id.submitbutton)
        val foodname: EditText = findViewById(R.id.foodname)
        val foodcount: EditText = findViewById(R.id.foodcount)

        submitbutton.setOnClickListener {
            val db = Firebase.firestore
            val docRef = db.collection("foodtypes").document("${foodname.text.trim()}")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.i(TAG, "DocumentSnapshot data: ${document.data}")
                        if (document.data == null){
                            val docs = hashMapOf(
                                "name" to foodname.text.toString().trim(),
                                "count" to foodcount.text.toString().toInt(),
                            )
                            db.collection("foodtypes").document("${foodname.text.trim()}")
                                .set(docs)
                                .addOnSuccessListener { Log.i(TAG, "DocumentSnapshot successfully written!") }
                                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
                        }
                        else{
                            Log.i(TAG, "document alr exists")
                            Log.i(TAG, "${document.get("count").toString().toInt() + foodcount.text.toString().toInt()}")
                            db.collection("foodtypes").document("${foodname.text.trim()}").update("count", document.get("count").toString().toInt() + foodcount.text.toString().toInt())
                        }
                    } else {
                        Log.i(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
            Toast.makeText(this, "item added successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, homepage::class.java).apply{}
            startActivity(intent)
        }
    }

    fun navigateToWallet(view: View){
        val intent = Intent(this, wallet::class.java).apply{}
        startActivity(intent)
    }

    fun navigateToProfile(view: View){
        val intent = Intent(this, profilePage::class.java).apply{}
        startActivity(intent)
    }
    fun navigateToSearch(view: View){
        val intent = Intent(this, foodtypes::class.java).apply{}
        startActivity(intent)
    }
    fun navigateToHome(view: View){
        val intent = Intent(this, homepage::class.java).apply{}
        startActivity(intent)
    }


}