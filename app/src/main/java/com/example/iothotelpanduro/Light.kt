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

class Light : AppCompatActivity() {

    val al = "area_light"
    val pul = "publicity_light"
    val prl = "principal_light"
    val rl = "right-light"
    val lel = "left_light"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light)

        val btnArea = findViewById<Button>(R.id.btnArea)
        val btnPublicity = findViewById<Button>(R.id.btnPublicity)
        val btnPrincipal = findViewById<Button>(R.id.btnPrincipal)
        val btnRight = findViewById<Button>(R.id.btnRight)
        val btnLeft = findViewById<Button>(R.id.btnLeft)

        //Lectura y escritura para Light Area
        val database1 = Firebase.database
        val myRef1 = database1.getReference(al)
        myRef1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot1: DataSnapshot) {
                var stateArea = snapshot1.getValue<String>()
                if(stateArea == "0"){
                    btnArea.setBackgroundResource(R.drawable.btn_off)
                }else{
                    btnArea.setBackgroundResource(R.drawable.btn_on)
                }

                btnArea.setOnClickListener{
                    if(checkInternet()) {
                        if (stateArea == "0") {
                            myRef1.setValue("1")
                            btnArea.setBackgroundResource(R.drawable.btn_on)
                        } else {
                            btnArea.setBackgroundResource(R.drawable.btn_off)
                            myRef1.setValue("0")
                        }
                    }else{
                        Toast.makeText(this@Light,"Sin conexion a Internet", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

        //Lectura y escritura para Light Publicity
        val database2 = Firebase.database
        val myRef2 = database2.getReference(pul)
        myRef2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot2: DataSnapshot) {
                var statePublcity = snapshot2.getValue<String>()
                if(statePublcity == "0"){
                    btnPublicity.setBackgroundResource(R.drawable.btn_off)
                }else{
                    btnPublicity.setBackgroundResource(R.drawable.btn_on)
                }

                btnPublicity.setOnClickListener{
                    if(checkInternet()) {
                        if (statePublcity == "0") {
                            myRef2.setValue("1")
                            btnPublicity.setBackgroundResource(R.drawable.btn_on)
                        } else {
                            btnPublicity.setBackgroundResource(R.drawable.btn_off)
                            myRef2.setValue("0")
                        }
                    }else{
                        Toast.makeText(this@Light,"Sin conexion a Internet", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

        //Lectura y escritura para Light Principal
        val database3 = Firebase.database
        val myRef3 = database3.getReference(prl)
        myRef3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot3: DataSnapshot) {
                var statePrincipal = snapshot3.getValue<String>()
                if(statePrincipal == "0"){
                    btnPrincipal.setBackgroundResource(R.drawable.btn_off)
                }else{
                    btnPrincipal.setBackgroundResource(R.drawable.btn_on)
                }

                btnPrincipal.setOnClickListener{
                    if(checkInternet()) {
                        if (statePrincipal == "0") {
                            myRef3.setValue("1")
                            btnPrincipal.setBackgroundResource(R.drawable.btn_on)
                        } else {
                            btnPrincipal.setBackgroundResource(R.drawable.btn_off)
                            myRef3.setValue("0")
                        }
                    }else{
                        Toast.makeText(this@Light,"Sin conexion a Internet", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

        //Lectura y escritura para Light Right
        val database4 = Firebase.database
        val myRef4 = database4.getReference(rl)
        myRef4.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot4: DataSnapshot) {
                var stateRight = snapshot4.getValue<String>()
                if(stateRight == "0"){
                    btnRight.setBackgroundResource(R.drawable.btn_off)
                }else{
                    btnRight.setBackgroundResource(R.drawable.btn_on)
                }

                btnRight.setOnClickListener {
                    if (checkInternet()) {
                        if (stateRight == "0") {
                            myRef4.setValue("1")
                            btnRight.setBackgroundResource(R.drawable.btn_on)
                        } else {
                            btnRight.setBackgroundResource(R.drawable.btn_off)
                            myRef4.setValue("0")
                        }
                    }else{
                        Toast.makeText(this@Light,"Sin conexion a Internet", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

        //Lectura y escritura para Light Left

        val database5 = Firebase.database
        val myRef5 = database5.getReference(lel)
        myRef5.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot5: DataSnapshot) {
                var stateLeft = snapshot5.getValue<String>()
                if(stateLeft == "0"){
                    btnLeft.setBackgroundResource(R.drawable.btn_off)
                }else{
                    btnLeft.setBackgroundResource(R.drawable.btn_on)
                }

                btnLeft.setOnClickListener {
                    if (checkInternet()) {
                        if (stateLeft == "0") {
                            myRef5.setValue("1")
                            btnLeft.setBackgroundResource(R.drawable.btn_on)
                        } else {
                            btnLeft.setBackgroundResource(R.drawable.btn_off)
                            myRef5.setValue("0")
                        }
                    }else{
                        Toast.makeText(this@Light,"Sin conexion a Internet", Toast.LENGTH_SHORT).show()
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