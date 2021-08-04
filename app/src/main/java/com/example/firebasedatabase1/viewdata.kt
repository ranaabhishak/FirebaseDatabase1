package com.example.firebasedatabase1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasedatabase1.viewdata
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.viewdata.*

class viewdata : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewdata)
        val database = Firebase.database
        val ref = database.getReference("maindata")
       var getdata=object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {}
        override fun onDataChange(snapshot: DataSnapshot) {
            var sb = StringBuilder()
            for (i in snapshot.children) {
                    var eusername = i.child("username").getValue()
                    var epassword = i.child("password").getValue()
                    var eemail = i.child("email").getValue()
                    sb.append("${i.key} $eusername $epassword $eemail\n")
                }
            if (sb.isNotBlank()){

                viewdata1.text = sb;
            }
            else
            {
                Toast.makeText(applicationContext, "no data is saved", Toast.LENGTH_SHORT).show()
    }}}
    ref.addValueEventListener(getdata)
    ref.addListenerForSingleValueEvent(getdata)
}}
