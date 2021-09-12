package com.example.freelancenepal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freelancenepal.R
import com.example.freelancenepal.adapter.GigAdapter
import com.example.freelancenepal.entity.Gig
import com.example.freelancenepal.repository.GigRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ViewGigActivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_gig)
        recyclerview = findViewById(R.id.recyclerview)
        getAllPet()
    }

    private fun getAllPet() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val petRepository = GigRepository()
                val response = petRepository.getAllPet()
                if(response.success == true){
                    //Pet details in listPet
                    val lstPets = response.data
                    withContext(Dispatchers.Main){
                        val adapter = GigAdapter(lstPets as ArrayList<Gig>, this@ViewGigActivity)
                        recyclerview.layoutManager = LinearLayoutManager(this@ViewGigActivity)
                        recyclerview.adapter = adapter
                    }
                }

            }catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@ViewGigActivity,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}