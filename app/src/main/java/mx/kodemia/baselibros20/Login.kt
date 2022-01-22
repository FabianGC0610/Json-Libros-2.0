package mx.kodemia.baselibros20

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
    private lateinit var tv_token:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
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
        tv_token = findViewById(R.id.tv_token)

        btn_ingresar.setOnClickListener {
            val cola = Volley.newRequestQueue(applicationContext)
            val url = "https://playground-bookstore.herokuapp.com/api/v1/login"
            val JsonObj:JSONObject = JSONObject()
            JsonObj.put("email", tiet_correo.text)
            JsonObj.put("password", tiet_contrasena.text)
            JsonObj.put("device_name", tiet_dispositivo.text)
            val peticion = JsonObjectRequest(Request.Method.POST, url,JsonObj, Response.Listener {
                response ->
                Log.d(TAG,response.toString())
                val json = JSONObject(response.toString())
                val TOKEN = json["plain-text-token"]

                tv_token.visibility = View.VISIBLE
                tv_token.text = getString(R.string.tv_token_string,TOKEN)
            },{
                error ->
                Log.e(TAG,error.toString())
            })
            cola.add(peticion)
        }
    }
}