package com.example.moviesapp
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java


class MainActivity : AppCompatActivity() {
    lateinit var btn_registrar: Button
    lateinit var campo_correo: EditText
    lateinit var campo_contra: EditText
    lateinit var btn_ingresar: Button
    lateinit var btn_contrasena: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn_registrar = findViewById<Button>(R.id.btn_registrar)
        campo_correo = findViewById<EditText>(R.id.campo_correo)
        campo_contra = findViewById<EditText>(R.id.campo_contra)
        btn_ingresar = findViewById<Button>(R.id.btn_ingresar)

        btn_contrasena = findViewById<TextView>(R.id.btn_contrasena)

        btn_registrar.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        btn_ingresar.setOnClickListener {
            var usuario = campo_correo.text.toString()
            var contra = campo_contra.text.toString()
            if (usuario.isBlank() || contra.isBlank()) {
                Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (usuario == "admin" && contra == "admin") {
                Toast.makeText(this, "Ingreso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Datos Incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
        btn_contrasena.setOnClickListener {
            startActivity(Intent(this, RecuperarActivity::class.java))
        }
    }
}