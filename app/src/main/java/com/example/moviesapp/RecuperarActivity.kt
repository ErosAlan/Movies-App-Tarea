package com.example.moviesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RecuperarActivity : AppCompatActivity() {
    lateinit var campo_correo_rec: EditText
    lateinit var btn_enviar: Button
    lateinit var btn_cancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recuperar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        campo_correo_rec = findViewById(R.id.campo_correo_rec)
        btn_enviar = findViewById(R.id.btn_enviar)
        btn_cancelar = findViewById(R.id.btn_cancelar)

        btn_enviar.setOnClickListener {
            val correo = campo_correo_rec.text.toString().trim()
            if (correo.isEmpty()) {
                Toast.makeText(this, "Ingresa tu correo electrónico", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                Toast.makeText(this, "El correo no es válido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Se envió un enlace de recuperación a $correo", Toast.LENGTH_LONG).show()
            }
        }

        btn_cancelar.setOnClickListener { finish() }
    }
}