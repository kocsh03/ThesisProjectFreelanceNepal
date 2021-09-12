package com.example.freelancenepal.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.freelancenepal.R
import java.lang.Exception

class FeedbackActivity : AppCompatActivity() {
    private lateinit var recipient: EditText
    private lateinit var subject: EditText
    private lateinit var message: EditText
    private lateinit var btnsubmit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        recipient = findViewById(R.id.recipient)
        subject = findViewById(R.id.subject)
        message = findViewById(R.id.message)
        btnsubmit = findViewById(R.id.btnsubmit)

        btnsubmit.setOnClickListener{
            var recipient= recipient.text.toString().trim()
            var subject = subject.text.toString().trim()
            var message = message.text.toString().trim()

            submit(recipient ,subject,message)


        }
    }
    private fun submit(recipient: String, subject: String, messsage: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"

        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT,subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, messsage)

        try{
            startActivity(Intent.createChooser(mIntent,"Choose Email Client..."))
        }
        catch (e: Exception){
            Toast.makeText(this,e.message, Toast.LENGTH_LONG).show()
        }

    }


}


