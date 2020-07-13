package com.example.bookofrecipes.activities.activity_register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookofrecipes.activities.activity_login.LoginActivity
import com.example.bookofrecipes.R
import com.example.bookofrecipes.activities.activity_navigation.NavigationActivity
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        val login = findViewById<EditText>(R.id.login_register_activity)
        val password = findViewById<EditText>(R.id.password_register_activity)
        val confirmPassword = findViewById<EditText>(R.id.confirm_password_register_activity)
        val btRegister = findViewById<Button>(R.id.btRegister_register_activity)
        val goToLogin = findViewById<TextView>(R.id.goToLogin)
        val registerDataVerification = RegisterDataVerification()

        goToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btRegister.setOnClickListener {
            if (registerDataVerification.validation(
                    login,
                    password,
                    confirmPassword
                )
            ) {
                sendJSON(login, password)
            } else
                return@setOnClickListener
        }
    }

    private fun sendJSON(login: EditText, password: EditText) {
        val jsonObject = JSONObject()

        jsonObject.put("login", login.text.toString())
        jsonObject.put("password", password.text.toString())

        submit(jsonObject, login)
    }

    private fun submit(jsonObject: JSONObject, login: EditText) {
        //Instantiate the RequestQueue.
        val url = "http://10.0.2.2:8080/api/v1/user/register"
        val requestQueue = Volley.newRequestQueue(this)
        val intent = Intent(this, NavigationActivity::class.java)


        val request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
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