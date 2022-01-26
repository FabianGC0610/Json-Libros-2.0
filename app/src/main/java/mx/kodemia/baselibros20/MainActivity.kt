package mx.kodemia.baselibros20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import mx.kodemia.baselibros20.extra.eliminarSesion
import mx.kodemia.baselibros20.extra.obtenerkDeSesion

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.qualifiedName
    private lateinit var btn_cerrar_sesion: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    fun init(){
        btn_cerrar_sesion = findViewById(R.id.btn_cerrar_sesion)
        btn_cerrar_sesion.setOnClickListener {
            val cola = Volley.newRequestQueue(applicationContext)
            val peticion = object: StringRequest(Request.Method.POST,getString(R.string.url_servidor)+getString(R.string.api_logout),{
                response ->
                Log.d(TAG, "Todo salio bien")
                eliminarSesion(applicationContext)
                startActivity(Intent(this,Login::class.java))
                finish()
            },{
                error ->
                Log.e(TAG, error.toString())
            }){
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String,String>()
                    headers["Authorization"] = "Bearer ${obtenerkDeSesion(applicationContext,"token")}"
                    return headers
                }
            }
            /*val peticionDos = JsonObjectRequest(Request.Method.POST,getString(R.string.url_servidor)+getString(R.string.api_logout),null,{
                    response ->
            },{
                    error ->
            })
            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer "+ obtenerkDeSesion(applicationContext,"token")
            peticionDos.headers = headers*/
            cola.add(peticion)
        }
    }
}