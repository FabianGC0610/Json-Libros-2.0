package mx.kodemia.baselibros20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import mx.kodemia.baselibros20.extra.iniciarSesion
import mx.kodemia.baselibros20.extra.validarSesion
import org.json.JSONObject

class Login : AppCompatActivity() {

    private val TAG = Login::class.qualifiedName

    private lateinit var til_correo: TextInputLayout
    private lateinit var tiet_correo: TextInputEditText
    private lateinit var til_contrasena: TextInputLayout
    private lateinit var tiet_contrasena: TextInputEditText
    private lateinit var til_dispositivo: TextInputLayout
    private lateinit var tiet_dispositivo: TextInputEditText
    private lateinit var btn_ingresar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        if(validarSesion(applicationContext)){
            lanzarActivity()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    fun init(){
        til_correo = findViewById(R.id.til_correo)
        tiet_correo = findViewById(R.id.tiet_correo)
        til_contrasena = findViewById(R.id.til_contrasena)
        tiet_contrasena = findViewById(R.id.tiet_contrasena)
        til_dispositivo = findViewById(R.id.til_dispositivo)
        tiet_dispositivo = findViewById(R.id.tiet_dispositivo)
        btn_ingresar = findViewById(R.id.btn_ingresar)

        btn_ingresar.setOnClickListener {
            val cola = Volley.newRequestQueue(applicationContext)
            val JsonObj:JSONObject = JSONObject()
            JsonObj.put("email", tiet_correo.text)
            JsonObj.put("password", tiet_contrasena.text)
            JsonObj.put("device_name", tiet_dispositivo.text)
            val peticion = JsonObjectRequest(Request.Method.POST, getString(R.string.url_servidor)+getString(R.string.api_login),JsonObj, Response.Listener {
                response ->
                Log.d(TAG,response.toString())
                val json = JSONObject(response.toString())
                //val TOKEN = json["plain-text-token"]
                iniciarSesion(applicationContext,json)
                if(validarSesion(applicationContext)){
                    lanzarActivity()
                }
            },{
                error ->
                Log.e(TAG,error.toString())
            })
            cola.add(peticion)
        }
    }
    fun lanzarActivity(){
        val intent = Intent(this, MainActivity::class.java)
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}