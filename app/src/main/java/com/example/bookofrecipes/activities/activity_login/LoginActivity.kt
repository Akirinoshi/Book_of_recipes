package com.example.bookofrecipes.activities.activity_login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookofrecipes.R
import com.example.bookofrecipes.activities.activity_navigation.NavigationActivity
import com.example.bookofrecipes.activities.activity_register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val goToRegister = findViewById<TextView>(R.id.goToRegiser)
        val login = findViewById<EditText>(R.id.username_login_activity)
        val password = findViewById<EditText>(R.id.password_login_activity)
        val loginDataVerification = LoginDataVerification()


        goToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        btLogin_login_activity.setOnClickListener {
            if (loginDataVerification.validation(
                login,
                password
            )){
                sendJSON(login, password)
            } else
                return@setOnClickListener
        }
    }

    private fun sendJSON(login : EditText, password : EditText) {
        val jsonObject = JSONObject()

        jsonObject.put("login", login.text.toString())
        jsonObject.put("password", password.text.toString())

        submit(jsonObject, login)
    }

    private fun submit(jsonObject: JSONObject, login: EditText) {
        //Instantiate the RequestQueue.
        val url = "http://10.0.2.2:8080/api/v1/user/login"
        val requestQueue = Volley.newRequestQueue(this)
        val preferences : SharedPreferences = getSharedPreferences("myPreferences" , Context.MODE_PRIVATE)
        val intent = Intent(this, NavigationActivity::class.java)


        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            Response.Listener { response ->
                if (response.get("uuid").toString() == "null") {
                    login.error = response.get("error").toString()
                } else {
                    setSharedPreference("globalPreference", "UUID", response.get("uuid").toString())
                    startActivity(intent)
                    finish()
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            })

        requestQueue.add(request)
    }

    private fun Context.setSharedPreference(prefsName: String, key: String, value: String) {
        getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            .edit().apply { putString(key, value); apply() }
    }
}