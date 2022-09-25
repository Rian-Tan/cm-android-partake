package com.partake.partake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.partake.partake.MainActivity.Companion.username
import com.partake.partake.MainActivity.Companion.email

class homepage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        val welcomeText: TextView = findViewById(R.id.textView)
        val welcomeUser = "Welcome $username"
        welcomeText.setText(welcomeUser)

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

    fun addstuff(view:View) {
        val intent = Intent(this, addstuff()::class.java).apply{}
        startActivity(intent)
    }
}
