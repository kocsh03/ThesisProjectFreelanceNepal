package com.example.freelancenepal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.freelancenepal.R

class RoleActivity : AppCompatActivity() {

    private lateinit var imgRoleClient: ImageView
    private lateinit var imgRoleFreelance: ImageView
    private lateinit var client: TextView
    private lateinit var freelancer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`activity_role_activity`)

        imgRoleClient = findViewById(R.id.imgRoleClient)
        imgRoleFreelance = findViewById(R.id.imgRoleFreelance)
        client = findViewById(R.id.textViewC)
        freelancer = findViewById(R.id.textViewF)



        imgRoleClient.setOnClickListener {
            val intent = Intent(this@RoleActivity, ClientActivity::class.java)
            startActivity(intent)
        }
        imgRoleFreelance.setOnClickListener {
            val intent1 = Intent(this@RoleActivity, MainActivity::class.java)
            startActivity(intent1)
        }
        client.setOnClickListener {
            val intent = Intent(this@RoleActivity, ClientActivity::class.java)
            startActivity(intent)
        }
        freelancer.setOnClickListener {
            val intent = Intent(this@RoleActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}