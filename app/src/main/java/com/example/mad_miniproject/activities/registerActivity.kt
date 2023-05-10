package com.example.mad_miniproject.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mad_miniproject.R
import com.example.mad_miniproject.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class registerActivity : AppCompatActivity() {

    private lateinit var regname: EditText
    private lateinit var regemail: EditText
    private lateinit var regphone: EditText
    private lateinit var reguname: EditText
    private lateinit var regpass: EditText
    private lateinit var regrepass: EditText
    private lateinit var regbtn: Button

    private lateinit var dbRef: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var textSignIn=findViewById<TextView>(R.id.textSignIn)
        textSignIn.setOnClickListener {
            val intent= Intent (this, signInActivity::class.java)
            startActivity(intent)

        }

        firebaseAuth = FirebaseAuth.getInstance()

        regname = findViewById(R.id.editregName)
        regemail = findViewById(R.id.emailEt)
        regphone = findViewById(R.id.editregPhn)
        reguname = findViewById(R.id.editregUserName)
        regpass = findViewById(R.id.passET)
        regrepass = findViewById(R.id.confirmPassEt)
        regbtn = findViewById(R.id.regsignUpBtn)

        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        regbtn.setOnClickListener {
            saveBookingData()
        }
    }

    private fun saveBookingData(){
        //getting values
        val uname= regname.text.toString()
        val email= regemail.text.toString()
        val uphone= regphone.text.toString()
        val uusername= reguname.text.toString()
        val pass= regpass.text.toString()
        val confirmPass= regpass.text.toString()


        if (uname.isNotEmpty() && email.isNotEmpty() && uphone.isNotEmpty() && uusername.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
            if (pass == confirmPass) {

                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent11 = Intent(this, signInActivity::class.java)
                        startActivity(intent11)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

        }



        val uemail = dbRef.push().key!!

        val users = UserModel(uname, email, uphone, uusername, pass)

        dbRef.child(uemail).setValue(users)
            .addOnCompleteListener{
                Toast.makeText(this,"Register Successfully", Toast.LENGTH_LONG).show()

                regname.text.clear()
                regemail.text.clear()
                regphone.text.clear()
                reguname.text.clear()
                regpass.text.clear()
                regrepass.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}