package com.example.mad_miniproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mad_miniproject.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class signUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textSignIn.setOnClickListener {
            val intent = Intent(this, signInActivity::class.java)
            startActivity(intent)
        }
        binding.regsignUpBtn.setOnClickListener {
            val name = binding.editregName.text.toString()
            val email = binding.emailEt.text.toString()
            val phone = binding.editregPhn.text.toString()
            val username = binding.editregUserName.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && username.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
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
        }

    }
}