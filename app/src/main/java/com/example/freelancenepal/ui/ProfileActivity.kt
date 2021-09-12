package com.example.freelancenepal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.freelancenepal.R
import com.example.freelancenepal.api.ServiceBuilder
import com.example.freelancenepal.entity.Freelancer
import com.example.freelancenepal.repository.FreelancerRepository
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.drawyerLayout
import kotlinx.android.synthetic.main.activity_profile.nav_view
import kotlinx.android.synthetic.main.activity_user_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileActivity : AppCompatActivity() {
    private lateinit var firstname:EditText
    private lateinit var lastname:EditText
    private lateinit var name: EditText
    private lateinit var email: EditText

    private lateinit var profileupdate: Button
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        firstname = findViewById(R.id.Profilefirstname)
        lastname = findViewById(R.id.ProfileLastname)
        name = findViewById(R.id.Profilename)
        email = findViewById(R.id.Profileemail)

        profileupdate = findViewById(R.id.btnprofileupdate)
        loadUserDetails()
        btnprofileupdate.setOnClickListener {
            updateprofile()
        }

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


    private fun loadUserDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = FreelancerRepository()
                val response = userRepo.getMe()

                if (response.success == true) {

//                    Log.d("User Id",fName.toString())
                    withContext(Dispatchers.Main) {
                        firstname.setText(response.data?.fname)
                        lastname.setText(response.data?.lname)
                        name.setText(response.data?.username)
                        email.setText(response.data?.email)


                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ProfileActivity,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    private fun updateprofile() {
        val firstname = firstname.text.toString()
        val lastname = lastname.text.toString()
        val username = name.text.toString()
        val email =  email.text.toString()


        val user =
            Freelancer(fname = firstname, lname = lastname, username = username, email = email)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = FreelancerRepository()
                val response = ServiceBuilder.id?.let { userRepo.updateUser(it, user) }
                if (response != null) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@ProfileActivity,
                            "Success", Toast.LENGTH_SHORT
                        ).show()


                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ProfileActivity,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT
                    ).show()
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


