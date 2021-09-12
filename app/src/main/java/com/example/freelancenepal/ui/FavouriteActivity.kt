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
import com.example.freelancenepal.adapter.FavouriteAdapter
import com.example.freelancenepal.entity.Favourite
import com.example.freelancenepal.repository.FavouriteRepo
import kotlinx.android.synthetic.main.activity_user_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteActivity : AppCompatActivity() {
    private lateinit var recyclerViewFavourite:RecyclerView
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        recyclerViewFavourite = findViewById(R.id.recyclerViewFavourite)
        loadFavouriteItems()


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

    private fun loadFavouriteItems() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val favouriteRepo = FavouriteRepo()
                val response = favouriteRepo.getFavouriteItems()
                if (response.success == true){
                    val lstPets = response.data
                    withContext(Dispatchers.Main){
                        val adapter = FavouriteAdapter(lstPets as ArrayList<Favourite>, this@FavouriteActivity)
                        recyclerViewFavourite.layoutManager = LinearLayoutManager(this@FavouriteActivity)
                        recyclerViewFavourite.adapter = adapter
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FavouriteActivity,
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