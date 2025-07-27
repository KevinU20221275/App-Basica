package net.kmontano.programacionoo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var miTelefono: Telefono
    private lateinit var textViewResultado: TextView
    private lateinit var layoutContenido: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        val btnLlamarTelefono = findViewById<Button>(R.id.btnLlamarTelefono)
        layoutContenido = findViewById(R.id.layoutContenido)

        // Configurar el botón inicial
        btnLlamarTelefono.setOnClickListener {
            // Inicializar Telefono solo al hacer clic
            miTelefono = Telefono()
            layoutContenido.visibility = View.VISIBLE // Mostrar el contenido
            btnLlamarTelefono.visibility = View.GONE // Ocultar el botón inicial
        }

        // Configurar el botón "Usar Batería"
        val btnUsarBateria = findViewById<Button>(R.id.btnUsarBateria)
        btnUsarBateria.setOnClickListener {
            miTelefono.usarBateria(30) // Usar 30% de batería
            actualizarTexto()
        }

        // Configurar el botón "Cargar Batería"
        val btnCargarBateria = findViewById<Button>(R.id.btnCargarBateria)
        btnCargarBateria.setOnClickListener {
            miTelefono.cargarBateria(50) // Cargar 50% de batería
            actualizarTexto()
        }

        // Configurar el boton "Recargar Saldo"
        val btnRecargarSaldo = findViewById<Button>(R.id.btnRecargarSaldo)
        btnRecargarSaldo.setOnClickListener {

            val mensaje : String = miTelefono.recargarSaldo(2.00F) // recargara $2.00 de saldo
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
            actualizarTexto()
        }

        // Configurar el boton regalar saldo
        val editTextRegalarSaldo = findViewById<EditText>(R.id.editTextRegalarSaldo) // input para ingresar el monto a regalar
        val editTextNumeroDestino = findViewById<EditText>(R.id.editTextRegalarSaldoNumeroDestino)
        val btnRegalarSaldo = findViewById<Button>(R.id.btnRegalarSaldo)
        btnRegalarSaldo.setOnClickListener {
            val saldoTexto = editTextRegalarSaldo.text.toString()
            val numeroDestinoTexto = editTextNumeroDestino.text.toString()

            if (saldoTexto.isNotEmpty() && numeroDestinoTexto.isNotEmpty()){
                val mensaje : String = miTelefono.regalarSaldo(saldoTexto.toFloat(), numeroDestinoTexto) 

                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
                actualizarTexto()
            }
        }

        // Configurar el boton "Cambiar Tarifa"
        val editTextTarifa = findViewById<EditText>(R.id.editTextTarifa) // input para el monto de la tarifa
        val btnCambiarTarifa = findViewById<Button>(R.id.btnCambiarTarifa)
        btnCambiarTarifa.setOnClickListener {
            val tarifaTexto = editTextTarifa.text.toString()

            if (tarifaTexto.isNotEmpty()){
                val tarifa = tarifaTexto.toFloat()
                val mensaje: String = miTelefono.cambioDeTarifa(tarifa)
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
                actualizarTexto()
            }
        }

        // Configruar el boton "Asignar Precio"
        val editPrecioTelefono = findViewById<EditText>(R.id.editTextPrecio)
        val btnAsignarPrecio = findViewById<Button>(R.id.btnAsignarPrecio)
        btnAsignarPrecio.setOnClickListener {
            val textPrecio = editPrecioTelefono.text.toString()

            if (textPrecio.isNotEmpty()){
                val mensaje = miTelefono.comprarTelefono(textPrecio.toFloat())
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
                actualizarTexto()
            }
        }

        // Configurar el boton "Mostrar Información"
        val btnMostrarInformacion = findViewById<Button>(R.id.btnMostrarInformacion)
        btnMostrarInformacion.setOnClickListener {
            textViewResultado = findViewById(R.id.textViewMostrarInfo)
            actualizarTexto() // Mostrar estado inicial
            textViewResultado.visibility = View.VISIBLE
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textViewMostrarInfo)) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top,
                systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Función para actualizar el TextView
    private fun actualizarTexto() {
        textViewResultado.text = miTelefono.mostrarInformacion().trimIndent()
    }
}