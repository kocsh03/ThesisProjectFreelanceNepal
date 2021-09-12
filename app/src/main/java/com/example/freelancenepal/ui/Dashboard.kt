package com.example.freelancenepal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.freelancenepal.R

class Dashboard : AppCompatActivity() {



    private lateinit var btnAddPet : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        btnAddPet = findViewById(R.id.btnAddPet)






        btnAddPet.setOnClickListener {
            val intent = Intent(
                this, AddGigActivity::class.java
            )
            startActivity(intent)
        }




    }
}