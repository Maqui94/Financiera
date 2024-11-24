package com.unirfp.financiera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    private lateinit var botonvolver: Button
    private lateinit var botonenviar: Button
    private lateinit var textocontacto: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // **Inicializa el botón antes de usarlo**
        botonvolver = findViewById(R.id.botonvolver)
        botonenviar = findViewById(R.id.botonenviar)
        textocontacto = findViewById(R.id.textocontacto)
        // Configurar el listener del botón
        botonvolver.setOnClickListener {
            // Iniciar la nueva Activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        //Boton enviar
        botonenviar.setOnClickListener {
            enviarCorreo()
        }
    }

    private fun enviarCorreo() {
        val contenido = textocontacto.text.toString()

        if (contenido.isNotBlank()) {
            val uri = Uri.parse("mailto:").buildUpon()
                .appendQueryParameter("body", contenido)
                .build()

            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.setPackage("com.google.android.gm") // Especifica Gmail

            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "La aplicación de Gmail no está instalada.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor, ingresa un mensaje.", Toast.LENGTH_SHORT).show()
        }
    }
}