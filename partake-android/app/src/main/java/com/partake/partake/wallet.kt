package com.partake.partake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.partake.partake.MainActivity.Companion.walletBalance
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.partake.partake.MainActivity.Companion.email

class wallet : AppCompatActivity() {
    var docslist = arrayListOf<String>()
    val TAG = "MyActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        val walletText: TextView = findViewById(R.id.balance)
        val balanceDisplay = "$${walletBalance.toString()}"
        walletText.setText(balanceDisplay)
        val db = Firebase.firestore
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, docslist)
        val listView: ListView = findViewById(R.id.listView)
        val docRef = db.collection("history").document(email)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.i(TAG, "DocumentSnapshot data: ${document.data}")
                    document.data?.forEach { (key,value) ->
                        docslist.add(value.toString())
                        listView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }
    /*fun navigateToWallet(view: View){
        val intent = Intent(this, wallet::class.java).apply{}
        startActivity(intent)
    }*/

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