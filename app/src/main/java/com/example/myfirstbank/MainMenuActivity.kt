package com.example.myfirstbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myfirstbank.MyFirstBank.Companion.prefs
import com.example.myfirstbank.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import java.sql.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime

class MainMenuActivity : AppCompatActivity(){

    private var connectSQL = ConnectSQL()

       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

           val iduser = prefs.getId()
           val tvsaldoDig: TextView= findViewById(R.id.TV_SaldoDig)
           try{
               val txtsaldo: PreparedStatement = connectSQL.dbConn()?.prepareStatement("SELECT Cash FROM usuarios WHERE UserID = ?")!!
               txtsaldo.setString(1, iduser)
               val tvsaldo: ResultSet = txtsaldo.executeQuery()
               tvsaldo.next()
               tvsaldoDig.setText(tvsaldo.getString(1))
           }catch(ex: SQLException){
               Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
           }

        var btn_MenuMovimientos: Button = findViewById(R.id.btn_MenuMovimientos)
        btn_MenuMovimientos.setOnClickListener{ openMenuMovimientos() }


        var btn_MenuControlParental: Button = findViewById(R.id.btn_MenuControlParental)
        btn_MenuControlParental.setOnClickListener{ openControlParental() }

        var btn_Divisas: Button = findViewById(R.id.btn_Divisas)
        btn_Divisas.setOnClickListener { openDivisas() }

        var btn_ahorros: ExtendedFloatingActionButton = findViewById(R.id.btn_ahorros)
           btn_ahorros.setOnClickListener{ openAhorros() }

        var btn_perfil: ExtendedFloatingActionButton = findViewById(R.id.btn_perfil)
           btn_perfil.setOnClickListener{ openPerfil() }

    }



    fun openMenuMovimientos(){
        var intent = Intent(this, Movimientos::class.java)
        startActivity(intent)
    }
    fun openControlParental(){
        val idpcv = 1
        var intent = Intent(this, PCV_Activity::class.java)
        intent.putExtra("idpcv", idpcv.toString())
        startActivity(intent)
    }
    fun openDivisas(){
        val intent = Intent(this, ConversionDivisas::class.java)
        startActivity(intent)
    }
    fun openAhorros(){
        var intent = Intent(this, AhorroActivity::class.java)
        startActivity(intent)
    }
    fun openPerfil(){
        val idpcv = 2
        var intent = Intent(this, PCV_Activity::class.java)
        intent.putExtra("idpcv", idpcv.toString())
        startActivity(intent)
    }

}