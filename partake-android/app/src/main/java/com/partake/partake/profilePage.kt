package com.partake.partake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.partake.partake.MainActivity.Companion.username
import com.partake.partake.MainActivity.Companion.email

class profilePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
        val usernameText: TextView = findViewById(R.id.usernametext)
        val emailText: TextView = findViewById(R.id.emailtext)
        usernameText.setText(username)
        emailText.setText(email)
    }
    fun navigateToWallet(view: View){
        val intent = Intent(this, wallet::class.java).apply{}
        startActivity(intent)
    }

    /* fun navigateToProfile(view: View){
        val intent = Intent(this, profilePage::class.java).apply{}
        startActivity(intent)
    }*/
    fun navigateToSearch(view: View){
        val intent = Intent(this, foodtypes::class.java).apply{}
        startActivity(intent)
    }
    fun navigateToHome(view: View){
        val intent = Intent(this, homepage::class.java).apply{}
        startActivity(intent)
    }
}