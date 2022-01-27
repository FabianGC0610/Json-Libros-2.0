package mx.kodemia.baselibros20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import mx.kodemia.baselibros20.extra.estaEnLinea
import mx.kodemia.baselibros20.extra.iniciarSesion
import mx.kodemia.baselibros20.extra.mensajeEmergente
import org.json.JSONObject

class Registro : AppCompatActivity() {

    private val TAG = Registro::class.qualifiedName

    private lateinit var til_res_nombre: TextInputLayout
    private lateinit var tiet_res_nombre: TextInputEditText
    private lateinit var til_res_correo: TextInputLayout
    private lateinit var tiet_res_correo: TextInputEditText
    private lateinit var til_res_contrasena: TextInputLayout
    private lateinit var tiet_res_contrasena: TextInputEditText
    private lateinit var til_res_contrasena_dos: TextInputLayout
    private lateinit var tiet_res_contrasena_dos: TextInputEditText
    private lateinit var btn_registrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        init()
    }

    fun init(){
        til_res_nombre = findViewById(R.id.til_res_nombre)
        tiet_res_nombre = findViewById(R.id.tiet_res_nombre)
        til_res_correo = findViewById(R.id.til_res_correo)
        tiet_res_correo = findViewById(R.id.tiet_res_correo)
        til_res_contrasena = findViewById(R.id.til_res_contrasena)
        tiet_res_contrasena = findViewById(R.id.tiet_res_contrasena)
        til_res_contrasena_dos = findViewById(R.id.til_res_contrasena_dos)
        tiet_res_contrasena_dos = findViewById(R.id.tiet_res_contrasena_dos)
        btn_registrar = findViewById(R.id.btn_registrar)

        btn_registrar.setOnClickListener {
            if(validarNombre() && validarCorreo() && validarContrasena() && validarContrasenaDos()){
                realizarPeticion()
            }
        }
    }

    fun realizarPeticion(){
        if(estaEnLinea(applicationContext)){
            val json = JSONObject()
            json.put("name", tiet_res_nombre.text)
            json.put("email", tiet_res_correo.text)
            json.put("password", tiet_res_contrasena.text)
            json.put("password_confirmation", tiet_res_contrasena_dos.text)
            json.put("device_name","Solovino's phone")
            val cola = Volley.newRequestQueue(applicationContext)
            val peticion = JsonObjectRequest(Request.Method.POST,getString(R.string.url_servidor)+getString(R.string.api_registro),json, {
                    response ->
                Log.d(TAG,response.toString())
                val jsonObject = JSONObject(response.toString())
                iniciarSesion(applicationContext,jsonObject)
                val intent = Intent(this,MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                },{
                    error ->
                    Log.e(TAG,error.toString())
            })
            cola.add(peticion)
        }else{
            mensajeEmergente(this,getString(R.string.error_internet))
        }
    }

    private fun validarCorreo(): Boolean{
        return if(tiet_res_correo.text.toString().isEmpty()){
            til_res_correo.error = getString(R.string.campo_vacio)
            false
        }else{
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(tiet_res_correo.text.toString()).matches()){
                til_res_correo.isErrorEnabled = false
                true
            }else{
                til_res_correo.error = getString(R.string.error_correo)
                false
            }
        }
    }

    private fun validarContrasena(): Boolean{
        return if(tiet_res_contrasena.text.toString().isEmpty()){
            til_res_contrasena.error = getString(R.string.campo_vacio)
            false
        }else{
            til_res_contrasena.isErrorEnabled = false
            true
        }
    }

    private fun validarContrasenaDos(): Boolean{
        return if(tiet_res_contrasena_dos.text.toString().isEmpty()){
            til_res_contrasena_dos.error = getString(R.string.campo_vacio)
            false
        }else{
            til_res_contrasena_dos.isErrorEnabled = false
            true
        }
    }

    private fun validarNombre(): Boolean{
        return if(tiet_res_nombre.text.toString().isEmpty()){
            til_res_nombre.error = getString(R.string.campo_vacio)
            false
        }else{
            til_res_nombre.isErrorEnabled = false
            true
        }
    }
}