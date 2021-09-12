package com.example.freelancenepal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freelancenepal.R
import com.example.freelancenepal.adapter.GigAdapter
import com.example.freelancenepal.entity.Gig
import com.example.freelancenepal.repository.GigRepository
import kotlinx.android.synthetic.main.activity_user_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class FreelancerDashboard : AppCompatActivity() {

    private lateinit var recyclerview: RecyclerView
    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard)

        recyclerview = findViewById(R.id.recyclerview)
        getAllPet()

        toggle = ActionBarDrawerToggle(this, drawyerLayout, R.string.open, R.string.close)

        drawyerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }

                R.id.home->{
                    startActivity(Intent(this, FreelancerDashboard::class.java))
                }

                R.id.Menufavourite->{
                    startActivity(Intent(this, FavouriteActivity::class.java))
                }
                R.id.menuFeedback->{
                    startActivity(Intent(this, FeedbackActivity::class.java))
                }
                R.id.menuLogout->{
                    startActivity(Intent(this, MainActivity::class.java))
                }

            }
            true
        }
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
                        val adapter = GigAdapter(lstPets as ArrayList<Gig>, this@FreelancerDashboard)
                        recyclerview.layoutManager = LinearLayoutManager(this@FreelancerDashboard)
                        recyclerview.adapter = adapter
                    }
                }

            }catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@FreelancerDashboard,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}