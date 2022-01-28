package mx.kodemia.baselibros20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import mx.kodemia.baselibros20.dataclasslibros.datosLibro

class Detalles : AppCompatActivity() {
    private val TAG = Detalles::class.qualifiedName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)
        intent.extras?.let{
            val book = it.getSerializable("book") as datosLibro
            Log.d(TAG,book.attributes.title)
        }

    }
}