package org.codeway.newdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // createing data base

        val database = Firebase.database("https://newdatabase-c4169-default-rtdb.firebaseio.com/")
        val myRef = database.getReference("note")



        val btnSave : Button = findViewById(R.id.btnsave)

        val etNote : EditText = findViewById(R.id.etnote)
        val tvData : TextView = findViewById(R.id.tvData)

        btnSave.setOnClickListener {
            val data : String = etNote.text.toString()
            if (data.isEmpty())
            {
                etNote.error = "Note Cannot be Empty"
            }
            else
            {
                //data save
                myRef.setValue(data)
                Toast.makeText(this, "SUCCESS !!", Toast.LENGTH_SHORT).show()
            }
        }
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val value = dataSnapshot.getValue<String>()
                tvData.text= value
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })
    }
}