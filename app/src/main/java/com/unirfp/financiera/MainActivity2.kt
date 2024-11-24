package com.unirfp.financiera

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    private lateinit var botonHelp: Button
    private lateinit var edbrutofin: TextView
    private lateinit var ednetofin: TextView
    private lateinit var edretencion: TextView
    private lateinit var eddeduccion: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        botonHelp = findViewById(R.id.botonhelp)
        edbrutofin = findViewById(R.id.edbrutofin)
        ednetofin = findViewById(R.id.ednetofin)
        edretencion = findViewById(R.id.edretencion)
        eddeduccion= findViewById(R.id.eddeduccion)
        val resultado1 = intent.getDoubleExtra("resultado1", 0.0)
        val resultado2 = intent.getDoubleExtra("resultado2", 0.0)
        val resultado3 = intent.getDoubleExtra("resultado3", 0.0)
        val resultado4 = intent.getStringExtra("resultado4")
        edbrutofin.text = "Resultado: $resultado1"
        ednetofin.text= "Resultado: $resultado2"
        edretencion.text="$resultado3 %"
        eddeduccion.text="$resultado4"
        // Configurar el listener del botón
        botonHelp.setOnClickListener {
            // Iniciar la nueva Activity
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
}