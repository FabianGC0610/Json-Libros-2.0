package mx.kodemia.baselibros20.extra

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import mx.kodemia.baselibros20.R
import org.json.JSONObject

fun obtenerPreferencias(context: Context): SharedPreferences{
    return EncryptedSharedPreferences.create(context.getString(
        R.string.name_file_preferences),
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

fun iniciarSesion(context: Context, jsonObject: JSONObject){
    val sharePreferences = obtenerPreferencias(context)
    with(sharePreferences.edit()){
        putString("token",jsonObject.getString(context.getString(R.string.key_token)))
        // Se puede tambien asi jsonObject[context.getString(R.string.key_token)].toString()
        apply()
    }
}

fun validarSesion(context: Context): Boolean{
    val sharedPreferences = obtenerPreferencias(context)
    val token = sharedPreferences.getString("token","")
    return !token.isNullOrEmpty()
}

fun obtenerkDeSesion(context: Context, clave: String): String{
    val sharedPreferences = obtenerPreferencias(context)
    return sharedPreferences.getString(clave,"").toString()
}

fun eliminarSesion(context: Context){
    val sharedPreferences = obtenerPreferencias(context)
    with(sharedPreferences.edit()){
        clear()
        apply()
    }
}