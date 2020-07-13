package com.example.bookofrecipes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookofrecipes.activities.activity_login.LoginActivity
import com.example.bookofrecipes.activities.activity_navigation.NavigationActivity
import com.example.bookofrecipes.activities.activity_register.RegisterActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        autoLogin()

        setContentView(R.layout.activity_main)

        btLogIn_main_activity.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btRegister_main_activity.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun autoLogin() {
        if (getSharedPreference("globalPreference", "UUID") != "Value is empty!") {
            println(getSharedPreference("globalPreference", "UUID"))
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun Context.getSharedPreference(prefsName: String, key: String): String {
        getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            ?.getString(key, "Value is empty!")?.let { return it }
        return "Preference doesn't exist."
    }
}



