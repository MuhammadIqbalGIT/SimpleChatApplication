package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var btnLogin : Button

    //firebaseAuth
    private lateinit var mAuth : FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //firebase
        mAuth = FirebaseAuth.getInstance()





        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btnLogin)
        val btnSignUp : Button = findViewById(R.id.btnSignUp)


        btnSignUp.setOnClickListener {
            val intent = Intent (this,SignUp::class.java)
            startActivity(intent)
        }



//firebase cek button login
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

          login(email,password)
        }


    }
    private fun login (email : String, password : String) {
        //logic for logging user

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //cek juga takut salah
                    val intent = Intent (this@MainActivity,Home::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@MainActivity, "User does not exist",Toast.LENGTH_SHORT).show()
                }
            }


    }
}