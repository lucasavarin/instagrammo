package thushyanthan.scott.javalynx.instagrammo.util.sharedPrefs

import android.content.SharedPreferences
import android.content.Context

class SharedPrefs(context: Context) {
    private val PREFS_NAME = "kotlincodes"
    private val USERNAME = "username"
    private val PASSWORD = "password"
    private val TOKEN = "token"
    private val PROFILO_UTENTE = "profiloUtente"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

     var username : String
        get() = sharedPref.getString(USERNAME, "")?: ""
        set(value) = sharedPref.edit().putString(USERNAME, value).apply()

     var password : String
        get() = sharedPref.getString(PASSWORD, "")?: ""
        set(value) = sharedPref.edit().putString(PASSWORD, value).apply()

     var token : String
         get() = sharedPref.getString(TOKEN, "")?: ""
        set(value) = sharedPref.edit().putString(TOKEN, value).apply()

     var profiloUtente : String
         get() = sharedPref.getString(PROFILO_UTENTE, "")?: ""
        set(value) = sharedPref.edit().putString(PROFILO_UTENTE, value).apply()

}
