package com.example.iothotelpanduro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.CompoundButton
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.view.isGone
import com.example.iothotelpanduro.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
            binding.btnIngresar.setOnClickListener {
                val Email = binding.txtEmail.text.toString()
                val Password = binding.txtPassword.text.toString()
                when {
                    Email.isEmpty() || Password.isEmpty() -> {
                        Toast.makeText(baseContext, "Correo o contraseña incorrectos.",Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(baseContext, "Ingresando...",Toast.LENGTH_SHORT).show()
                        SignIn(Email, Password)
                    }
                }
            }
    }

    private fun SignIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                if(task.isSuccessful){
                    Log.d("TAG", "Ingresando")
                    reload()
                }else{
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Correo o contraseña incorrectos",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun reload() {
        startActivity(Intent(this,windowsOptions::class.java))
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            startActivity(Intent(this,windowsOptions::class.java))
            finish()
        }
    }
}