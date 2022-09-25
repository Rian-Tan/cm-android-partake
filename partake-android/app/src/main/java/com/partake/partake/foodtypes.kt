package com.partake.partake

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class foodtypes : AppCompatActivity() {
    var docslist = arrayListOf<String>()
    val TAG = "MyActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodtypes)
        val db = Firebase.firestore
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, docslist)
        val listView: ListView = findViewById(R.id.listview2)
        val docRef = db.collection("foodtypes")
        val foodcard: CardView = findViewById(R.id.foodcardview)
        val foodnametext: TextView = findViewById(R.id.foodnametext)
        val foodcounttext: TextView = findViewById(R.id.foodcounttext)
        val closebutton: Button = findViewById(R.id.button4)


        //to disappear
        val textView7: TextView = findViewById(R.id.textView7)
        val input: EditText = findViewById(R.id.editTextTextPersonName)
        val searchbutton: ImageButton = findViewById(R.id.imageButton20)

        db.collection("foodtypes").get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot?> { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        docslist.add(document.id)
                        listView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    Log.d(TAG, docslist.toString())
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            })
        listView.setOnItemClickListener(){ parent, view, position, id ->
            listView.visibility = View.INVISIBLE
            input.visibility = View.INVISIBLE
            textView7.visibility = View.INVISIBLE
            searchbutton.visibility = View.INVISIBLE
            foodcard.visibility = View.VISIBLE
            closebutton.visibility = View.VISIBLE

            val db = Firebase.firestore
            val docRef = db.collection("foodtypes").document("${docslist[position]}")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.i(TAG, "DocumentSnapshot data: ${document.data}")
                        foodnametext.setText("item: ${document.get("name")}")
                        foodcounttext.setText("count: ${document.get("count")}")
                    } else {
                        Log.i(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }

            closebutton.setOnClickListener(){
                listView.visibility = View.VISIBLE
                input.visibility = View.VISIBLE
                textView7.visibility = View.VISIBLE
                searchbutton.visibility = View.VISIBLE
                foodcard.visibility = View.INVISIBLE
                closebutton.visibility = View.INVISIBLE
            }
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
    /*fun navigateToSearch(view: View){
        val intent = Intent(this, foodtypes::class.java).apply{}
        startActivity(intent)
    } */
    fun navigateToHome(view:View){
        val intent = Intent(this, homepage::class.java).apply{}
        startActivity(intent)
    }

    fun search(view: View){
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, docslist)
        val listView: ListView = findViewById(R.id.listview2)
        val input: EditText = findViewById(R.id.editTextTextPersonName)
        docslist.clear()
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
        val db = Firebase.firestore
        Log.i(TAG, "${input.text}")
        db.collection("foodtypes")
            .whereEqualTo("name", "${input.text.trim()}")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    docslist.add(document.id.toString())
                    listView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

    }
}