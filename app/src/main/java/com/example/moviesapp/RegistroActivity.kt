package com.example.moviesapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class RegistroActivity : AppCompatActivity() {
    lateinit var campo_nombre: EditText
    lateinit var campo_correo_reg: EditText
    lateinit var campo_contra_reg: EditText
    lateinit var campo_verificar: EditText
    lateinit var campo_fecha: EditText
    lateinit var btn_registrarse: Button

    var fechaNacimiento: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        campo_nombre = findViewById(R.id.campo_nombre)
        campo_correo_reg = findViewById(R.id.campo_correo_reg)
        campo_contra_reg = findViewById(R.id.campo_contra_reg)
        campo_verificar = findViewById(R.id.campo_verificar)
        campo_fecha = findViewById(R.id.campo_fecha)
        btn_registrarse = findViewById(R.id.btn_registrarse)

        campo_fecha.setOnClickListener { mostrarCalendario() }
        btn_registrarse.setOnClickListener { validarRegistro() }
    }

    private fun mostrarCalendario() {
        val hoy = Calendar.getInstance()
        val dialogo = DatePickerDialog(
            this,
            { _, anio, mes, dia ->
                val fecha = Calendar.getInstance()
                fecha.set(anio, mes, dia)
                fechaNacimiento = fecha
                campo_fecha.setText("%02d/%02d/%04d".format(dia, mes + 1, anio))
            },
            hoy.get(Calendar.YEAR) - 18,
            hoy.get(Calendar.MONTH),
            hoy.get(Calendar.DAY_OF_MONTH)
        )
        dialogo.datePicker.maxDate = hoy.timeInMillis // no permite fechas futuras
        dialogo.show()
    }

    private fun calcularEdad(nacimiento: Calendar): Int {
        val hoy = Calendar.getInstance()
        var edad = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR)
        val aunNoCumple = hoy.get(Calendar.MONTH) < nacimiento.get(Calendar.MONTH) ||
                (hoy.get(Calendar.MONTH) == nacimiento.get(Calendar.MONTH) &&
                        hoy.get(Calendar.DAY_OF_MONTH) < nacimiento.get(Calendar.DAY_OF_MONTH))
        if (aunNoCumple) edad--
        return edad
    }

    private fun validarRegistro() {
        val nombre = campo_nombre.text.toString().trim()
        val correo = campo_correo_reg.text.toString().trim()
        val contra = campo_contra_reg.text.toString()
        val verificar = campo_verificar.text.toString()
        val fecha = fechaNacimiento

        if (nombre.isEmpty() || correo.isEmpty() || contra.isEmpty() ||
            verificar.isEmpty() || fecha == null) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "El correo no es válido", Toast.LENGTH_SHORT).show()
            return
        }

        if (contra != verificar) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        if (calcularEdad(fecha) < 18) {
            Toast.makeText(this, "Debes tener al menos 18 años", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show()
        finish()
    }
}