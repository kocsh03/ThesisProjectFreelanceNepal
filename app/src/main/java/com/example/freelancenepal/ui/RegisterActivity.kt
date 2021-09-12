package com.example.freelancenepal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.freelancenepal.R
import com.example.freelancenepal.entity.Freelancer
import com.example.freelancenepal.repository.FreelancerRepository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {
    private lateinit var edtfname: TextInputEditText
    private lateinit var edtlname: TextInputEditText
    private lateinit var edtemail: TextInputEditText
    private lateinit var edtusername: TextInputEditText
    private lateinit var edtpassword: TextInputEditText
    private lateinit var btnRegistration: Button
    private lateinit var btnLogin : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edtfname = findViewById(R.id.edtfname)
        edtlname = findViewById(R.id.edtlname)
        edtemail = findViewById(R.id.edtemail)
        edtusername = findViewById(R.id.edtusername)

        edtpassword = findViewById(R.id.edtpassword)
        btnRegistration = findViewById(R.id.btnRegistration)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)
        }

        btnRegistration.setOnClickListener {
            val fname = edtfname.text.toString()
            val lname = edtlname.text.toString()
            val email = edtemail.text.toString()
            val username = edtusername.text.toString()
            val password = edtpassword.text.toString()


            val intent = Intent(this@RegisterActivity, ContinueRegistration::class.java)
            startActivity(intent)

            if (password != password) {
                edtpassword.error = "Password does not match"
                edtpassword.requestFocus()
                return@setOnClickListener
            }else{
                val user = Freelancer(fname = fname,lname = lname, username =  username, email = email, password =  password)
                CoroutineScope(Dispatchers.IO).launch {
//                    UserDB.getInstance(this@RegisterActivity).getUserDAO().registerUser(user)
                    try {
                        val userRepository = FreelancerRepository()
                        val response = userRepository.registerUser(user)
                        if(response.success == true){
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                        this@RegisterActivity,
                                        "Register SuccessFull", Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                    this@RegisterActivity,
                                    "Username cannot be duplicate", Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
                }
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }
        }


    }

}