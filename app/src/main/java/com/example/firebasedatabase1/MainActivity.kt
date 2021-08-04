package com.example.firebasedatabase1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.viewdata.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val emailpattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
        val database = Firebase.database
        val ref = database.getReference("maindata")
        save.setOnClickListener {
            val username = useredit.text.toString()
            val password = passwordedit.text.toString()
            val email = emailedit.text.toString()
            if(username.isNotBlank() && password.isNotBlank() && email.isNotBlank()) {
                if (email.matches(emailpattern.toRegex())) {
                    val dataid = ref.push().key
                    val data = dataid?.let { Data(username, password, email) }
                    dataid?.let {
                        ref.child(dataid).setValue(data).addOnCompleteListener {
                            Toast.makeText(applicationContext, "saved", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else
                {
                    Toast.makeText(applicationContext, "enter valid email", Toast.LENGTH_SHORT).show()
                }
            }
        else
        {
            Toast.makeText(applicationContext, "please enter all details", Toast.LENGTH_SHORT).show()
        }}
        view.setOnClickListener{
            val intent= Intent(this@MainActivity, viewdata::class.java)
            startActivity(intent)


    }
}}
