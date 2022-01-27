package mx.kodemia.baselibros20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import kotlinx.serialization.json.Json
import mx.kodemia.baselibros20.dataclass.Empleado
import org.json.JSONObject
import java.lang.Exception
import kotlinx.serialization.decodeFromString
import mx.kodemia.baselibros20.dataclasslibros.Data
import mx.kodemia.baselibros20.extra.*

class Login : AppCompatActivity() {

    private val TAG = Login::class.qualifiedName

    private lateinit var til_correo: TextInputLayout
    private lateinit var tiet_correo: TextInputEditText
    private lateinit var til_contrasena: TextInputLayout
    private lateinit var tiet_contrasena: TextInputEditText
    private lateinit var til_dispositivo: TextInputLayout
    private lateinit var tiet_dispositivo: TextInputEditText
    private lateinit var btn_ingresar:Button
    private lateinit var pb_login: ProgressBar
    private lateinit var  tv_registrate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        eliminarSesion(applicationContext) //Para no estar iniciando sesion
        if(validarSesion(applicationContext)){
            lanzarActivity()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        pruebaJsonObject()
    }

    fun pruebaJsonObject(){
        val cadena = """
        {
            "plain-text-key": "168|2Ry3YdtTgi94zGYLA53Z4VKWaN0lku05mC55huTq"
        }
        """.trimIndent()
        val jsonObject = JSONObject(cadena)
        Log.e(TAG, jsonObject.getString("plain-text-key"))
        val jsonObjectDos = JSONObject()
        jsonObjectDos.put("nombre","Fabian")
        jsonObjectDos.put("correo","fabian.god06@gmail.com")
        jsonObjectDos.put("telefono","3312946549")

        Log.e(TAG,jsonObjectDos.toString())

        val cadenaDos = """
            {
                "nombre": "Pancho",
                "telefono": "3344444444",
                "casado": false,
                "cuotaHora": 158.28,
                "diasDeSemanaTrabajo":[
                    "lunes",
                    "martes",
                    "miercoles",
                    "jueves"
                ],
                "observaciones":[
                    {"fecha":"25/01/2021","comentario":"Todo bien"},
                    {"fecha":"02/01/2021","comentario":"Llego tarde"},
                    {"fecha":"01/01/2021","comentario":"Llego temprano xD"}
                ]
            }
        """.trimIndent()
        try{
            val empleado = Gson().fromJson(cadenaDos, Empleado::class.java)
            Log.d(TAG, empleado.nombre)
            Log.d(TAG,empleado.diasDeSemanaTrabajo.size.toString())
            for(dia in empleado.diasDeSemanaTrabajo){
                Log.d(TAG,dia)
            }
            for(observacion in empleado.observaciones){
                Log.d(TAG,observacion.fecha)
                Log.d(TAG,observacion.comentario)
            }
        }catch (e: Exception){
            Log.e(TAG,e.message.toString())
        }
        val cadenaTres = """
            [{
                "nombre": "Pancho",
                "telefono": "3344444444",
                "casado": false,
                "cuotaHora": 158.28,
                "diasDeSemanaTrabajo":[
                    "lunes",
                    "martes",
                    "miercoles",
                    "jueves"
                ],
                "observaciones":[
                    {"fecha":"25/01/2021","comentario":"Todo bien"},
                    {"fecha":"02/01/2021","comentario":"Llego tarde"},
                    {"fecha":"01/01/2021","comentario":"Llego temprano xD"}
                ]
            },{
                "nombre": "Fabian",
                "telefono": "3344444444",
                "casado": false,
                "cuotaHora": 158.28,
                "diasDeSemanaTrabajo":[
                    "lunes",
                    "martes",
                    "miercoles",
                    "jueves"
                ],
                "observaciones":[
                    {"fecha":"25/01/2021","comentario":"Todo bien"},
                    {"fecha":"02/01/2021","comentario":"Llego tarde"},
                    {"fecha":"01/01/2021","comentario":"Llego temprano xD"}
                ]
            }]
        """.trimIndent()
        val empleados = Gson().fromJson(cadenaTres,Array<Empleado>::class.java)
        for(empleado in empleados){
            Log.d(TAG,empleado.nombre)
        }

        val cadenaCuatro = """
            {
                "nombre": "Pancrasio",
                "telefono": "3344444444",
                "casado": false,
                "cuota_Hora": 158.28,
                "dias-De-Semana-Trabajo":[
                    "lunes",
                    "martes",
                    "miercoles",
                    "jueves"
                ],
                "observaciones":[
                    {"fecha":"25/01/2021","comentario":"Todo bien"},
                    {"fecha":"02/01/2021","comentario":"Llego tarde"},
                    {"fecha":"01/01/2021","comentario":"Llego temprano xD"}
                ]
            }
        """.trimIndent()
        val empleadoSerialization = Json.decodeFromString<Empleado>(cadenaCuatro)
        Log.d(TAG,empleadoSerialization.nombre)
        Log.d(TAG,empleadoSerialization.cuotaHora.toString())

        val cadenaLibro = """
            {
              "data": [
                {
                  "type": "books",
                  "id": "venganza-nuestra",
                  "attributes": {
                    "title": "Vengaza Nuestra",
                    "slug": "venganza-nuestra",
                    "content": "Narrativa sobre la postconquista de México",
                    "created-at": "2021-11-06T18:41:14+00:00",
                    "updated-at": "2021-11-06T18:41:14+00:00"
                  },
                  "relationships": {
                    "authors": {
                      "links": {
                        "self": "https://bookstore.test/api/v1/books/venganza-nuestra/relationships/authors",
                        "related": "https://bookstore.test/api/v1/books/venganza-nuestra/authors"
                      }
                    },
                    "categories": {
                      "links": {
                        "self": "https://bookstore.test/api/v1/books/venganza-nuestra/relationships/categories",
                        "related": "https://bookstore.test/api/v1/books/venganza-nuestra/categories"
                      }
                    }
                  },
                  "links": {
                    "self": "https://bookstore.test/api/v1/books/venganza-nuestra"
                  }
                },
                {
                  "type": "books",
                  "id": "la-mera-vena",
                  "attributes": {
                    "title": "La Mera Vena",
                    "slug": "la-mera-vena",
                    "content": "Narrativa sobre lo chingones que somos los de Kodemia",
                    "created-at": "2021-11-06T18:41:14+00:00",
                    "updated-at": "2021-11-06T18:41:14+00:00"
                  },
                  "relationships": {
                    "authors": {
                      "links": {
                        "self": "https://bookstore.test/api/v1/books/la-mera-vena/relationships/authors",
                        "related": "https://bookstore.test/api/v1/books/la-mera-vena/authors"
                      }
                    },
                    "categories": {
                      "links": {
                        "self": "https://bookstore.test/api/v1/books/la-mera-vena/relationships/categories",
                        "related": "https://bookstore.test/api/v1/books/la-mera-vena/categories"
                      }
                    }
                  },
                  "links": {
                    "self": "https://bookstore.test/api/v1/books/la-mera-vena"
                  }
                }
            ]
        }
        """.trimIndent()
        //val librosSerialization = Gson().fromJson(cadenaLibro,Data::class.java)
        val librosSerialization = Json.decodeFromString<Data>(cadenaLibro)
        try{
            for(libros in librosSerialization.data){
                Log.d(TAG,libros.id)
            }
            Log.d(TAG,librosSerialization.data[1].attributes.content)
            Log.d(TAG,librosSerialization.data[1].relationships.categories.links.self)
        }catch (e: Exception){
            Log.e(TAG,e.message.toString())
        }
    }

    fun init() {
        til_correo = findViewById(R.id.til_correo)
        tiet_correo = findViewById(R.id.tiet_correo)
        til_contrasena = findViewById(R.id.til_contrasena)
        tiet_contrasena = findViewById(R.id.tiet_contrasena)
        til_dispositivo = findViewById(R.id.til_dispositivo)
        tiet_dispositivo = findViewById(R.id.tiet_dispositivo)
        btn_ingresar = findViewById(R.id.btn_ingresar)
        pb_login = findViewById(R.id.pb_login)

        btn_ingresar.setOnClickListener {
            if (validarCorreo() && validarContrasena()) {
                realizarPeticion()
            }
        }
        tv_registrate = findViewById(R.id.tv_registrate)
        tv_registrate.setOnClickListener {
            startActivity(Intent(this,Registro::class.java))
        }
    }
    fun lanzarActivity(){
        val intent = Intent(this, MainActivity::class.java)
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun validarCorreo(): Boolean{
        return if(tiet_correo.text.toString().isEmpty()){
            til_correo.error = getString(R.string.campo_vacio)
            false
        }else{
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(tiet_correo.text.toString()).matches()){
                til_correo.isErrorEnabled = false
                true
            }else{
                til_correo.error = getString(R.string.error_correo)
                false
            }
        }
    }

    private fun validarContrasena(): Boolean{
        return if(tiet_contrasena.text.toString().isEmpty()){
            til_contrasena.error = getString(R.string.campo_vacio)
            false
        }else{
            til_contrasena.isErrorEnabled = false
            true
        }
    }

    fun realizarPeticion(){
        if(estaEnLinea(applicationContext)){
            btn_ingresar.visibility = View.GONE
            pb_login.visibility = View.VISIBLE
            val cola = Volley.newRequestQueue(applicationContext)
            val JsonObj:JSONObject = JSONObject()
            JsonObj.put("email", tiet_correo.text)
            JsonObj.put("password", tiet_contrasena.text)
            JsonObj.put("device_name", tiet_dispositivo.text)
            val peticion = JsonObjectRequest(Request.Method.POST,getString(R.string.url_servidor)+getString(R.string.api_login),JsonObj, Response.Listener {
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
                btn_ingresar.visibility = View.VISIBLE
                pb_login.visibility = View.GONE
                Log.e(TAG,error.toString())
            })
            cola.add(peticion)
        }else{
            mensajeEmergente(this,getString(R.string.error_internet))
        }
    }
}