package com.example.freelancenepal.ui

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.freelancenepal.R
import com.example.freelancenepal.api.ServiceBuilder
import com.example.freelancenepal.repository.FreelancerRepository
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       username = findViewById(R.id.etusername)
        password = findViewById(R.id.etpassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignUp)
        linearLayout = findViewById(R.id.linearlayout)

        btnLogin.setOnClickListener {
//            val intent = Intent(this@MainActivity, UserDashboard::class.java)
//            startActivity(intent)

            login()
        }

        btnSignUp.setOnClickListener {
            val intent1 = Intent( this@MainActivity, RegisterActivity::class.java)
            startActivity(intent1)
        }
    }

    private fun checkRunTimePermission() {
        if (!hasPermission()) {
            requestPermission()
        }
    }

    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
                break
            }
        }
        return hasPermission
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this@MainActivity, permissions, 1)
    }

    private fun login() {
        val username = username.text.toString()
        val password = password.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = FreelancerRepository()
                val response = repository.loginUser(username, password)

                if (response.success == true) {
                    ServiceBuilder.token = "Bearer ${response.token}"
                    loginSharedPref()
                    startActivity(
                        Intent(
                            this@MainActivity,
//                            Dashboard::class.java
                            FreelancerDashboard::class.java
                        )
                    )
                    finish()

                } else {
                    withContext(Dispatchers.Main) {
                        val snack =
                            Snackbar.make(
                                linearLayout,
                                "Invalid credentials",
                                Snackbar.LENGTH_LONG
                            )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    fun loginSharedPref() {
        val username = username.text.toString()
        val password = password.text.toString()
        val loginSharedPref = getSharedPreferences("LoginPref", MODE_PRIVATE)
        val editor = loginSharedPref.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.commit()
    }
}