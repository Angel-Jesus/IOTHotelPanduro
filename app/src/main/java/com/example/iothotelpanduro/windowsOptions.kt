package com.example.iothotelpanduro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.iothotelpanduro.databinding.ActivityMainBinding
import com.example.iothotelpanduro.databinding.ActivityWindowsOptionsBinding
import com.google.firebase.auth.FirebaseAuth

class windowsOptions : AppCompatActivity() {

    private lateinit var myauth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_windows_options)

        myauth = FirebaseAuth.getInstance()

        val btnLight1 = findViewById<Button>(R.id.btnLight)
        btnLight1.setOnClickListener {
            if(checkInternet()){
                startActivity(Intent(this,Light::class.java))
            }else{
             Toast.makeText(this,"Sin conexion a Internet",Toast.LENGTH_SHORT).show()
            }
        }

        val btnDoor1 = findViewById<Button>(R.id.btnDoor)
        btnDoor1.setOnClickListener{
            if(checkInternet()) {
                startActivity(Intent(this, Door::class.java))
            }else{
                Toast.makeText(this,"Sin conexion a Internet",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun checkInternet():Boolean {
        val conManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo =conManager.activeNetworkInfo
        return internetInfo!=null && internetInfo.isConnected
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sing_out,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.btn_signOut -> {
            myauth.signOut()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

