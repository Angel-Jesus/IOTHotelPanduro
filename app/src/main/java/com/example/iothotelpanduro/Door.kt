package com.example.iothotelpanduro

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class Door : AppCompatActivity() {
    val da = "door_hotel"
    val dH = "door_house"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_door)

        val btnHotel = findViewById<Button>(R.id.btnOpenHotel)
        val btnHouse = findViewById<Button>(R.id.btnOpenHouse)

        //Lectura y escritura para Door Hotel
        val database6 = Firebase.database
        val myRef6 = database6.getReference(da)
        myRef6.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot1: DataSnapshot) {
                var stateHotel = snapshot1.getValue<String>()
                if(stateHotel == "0"){
                    btnHotel.setBackgroundResource(R.drawable.btn_off)
                }else{
                    btnHotel.setBackgroundResource(R.drawable.btn_on)
                }
                btnHotel.setOnClickListener{
                    if(checkInternet()) {
                        myRef6.setValue("1")
                        btnHotel.setBackgroundResource(R.drawable.btn_on)
                    }else{
                        Toast.makeText(this@Door,"Sin conexion a Internet",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

        //Lectura y escritura para Door House
        val database7 = Firebase.database
        val myRef7 = database7.getReference(dH)
        myRef7.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot1: DataSnapshot) {
                var stateHouse = snapshot1.getValue<String>()
                if(stateHouse == "0"){
                    btnHouse.setBackgroundResource(R.drawable.btn_off)
                }else{
                    btnHouse.setBackgroundResource(R.drawable.btn_on)
                }
                btnHouse.setOnClickListener{
                    if(checkInternet()) {
                        myRef7.setValue("1")
                        btnHouse.setBackgroundResource(R.drawable.btn_on)
                    }else{
                        Toast.makeText(this@Door,"Sin conexion a Internet",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

    }

    private fun checkInternet():Boolean {
        val conManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo =conManager.activeNetworkInfo
        return internetInfo!=null && internetInfo.isConnected
    }

}