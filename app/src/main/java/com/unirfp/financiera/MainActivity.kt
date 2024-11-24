package com.unirfp.financiera

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //Crear variables privadas con inicializacion tardia
    private lateinit var botonHelp: Button
    private lateinit var inbruto:EditText
    private lateinit var inpagas:EditText
    private lateinit var inedad:EditText
    private lateinit var ingrupo:Spinner
    private lateinit var indisc:Spinner
    private lateinit var incivil:Spinner
    private lateinit var inhijos:EditText
    private lateinit var incuidar:EditText
    private lateinit var buttoncalc:Button
    var irpf :Double = 0.0
    var posibles:String=""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //init component
        this.inbruto = findViewById(R.id.inbruto)
        this.inpagas = findViewById(R.id.inpagas)
        this.inedad = findViewById(R.id.inedad)
        this.ingrupo = findViewById(R.id.ingrupo)
        this.indisc = findViewById(R.id.indisc)
        this.incivil = findViewById(R.id.incivil)
        this.inhijos = findViewById(R.id.inhijos)
        this.incuidar = findViewById(R.id.incuidar)
        this.buttoncalc = findViewById(R.id.buttoncalc)
        this.botonHelp = findViewById(R.id.botonhelp)

        // Configurar el listener del botón
        this.botonHelp.setOnClickListener {
            // Iniciar la nueva Activity
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
        this.buttoncalc.setOnClickListener{
            val num1 = this.inbruto.text.toString().toDoubleOrNull() //Convierte el texto a Double, retornando null si no es un número válido.
            val num2 = this.inpagas.text.toString().toIntOrNull() //Las pagas no pueden ser decimales
            val num3 = this.inedad.text.toString().toIntOrNull()
            val num4 = this.ingrupo.selectedItem.toString()
            val num5 = this.indisc.selectedItem.toString()
            val num6 = this.incivil.selectedItem.toString()
            val num7 = this.inhijos.text.toString().toIntOrNull()
            val num8 = this.incuidar.text.toString().toIntOrNull()

            if (num1 != null) {
                if (num1 <= 12450) {
                    irpf = 0.19
                } else if (num1 <= 20200) {
                    irpf = 0.24
                } else if (num1 <= 35200) {
                    irpf = 0.30
                } else if (num1 <= 60000) {
                    irpf = 0.37
                } else if (num1 <= 300000) {
                    irpf = 0.45
                } else {
                    irpf = 0.47
                }
            }
            if(num7!=0 || num8!=0 || num6 != "Casado"){
                posibles="SI TIENES, CONSULTALO"
            } else {
                posibles="NO TIENES POSIBLES DEDUCCIONES AUN"
            }

            if(num1 !=null && num2 !=null && num3 !=null && num7 !=null && num8 !=null) {
                val resultado1 = num1 / num2
                val resultado2 = num1*irpf/num2
                val resultado3= irpf*100
                val resultado4=posibles

                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("resultado1", resultado1)
                intent.putExtra("resultado2", resultado2)
                intent.putExtra("resultado3", resultado3)
                intent.putExtra("resultado4", resultado4)
                startActivity(intent)

            }else{
                Toast.makeText(this,"Por favor rellena todo.",Toast.LENGTH_SHORT).show()

            }

        }
    }
}