package br.com.hellen.data.localDataSource

import android.content.SharedPreferences
import br.com.hellen.data.models.UserModel
import com.google.gson.Gson

class UserLocalDataSource(private val sharedPreferences: SharedPreferences) {

    fun getUser(username:String): UserModel? {
        val userJSON = sharedPreferences.getString(username, "")
        userJSON?.let{
            return Gson().fromJson(userJSON, UserModel::class.java)
        }
        return null
    }

    fun putUser(user:UserModel){
        sharedPreferences.edit().apply{
            putString("nome", user.nome)
            putString("sobrenome", user.sobrenome)
            putString("curso", user.curso)
            putString("email", user.email)
            putString("senha", user.senha)
        }.apply()
    }

    fun deleteUser(user:UserModel){
        sharedPreferences.edit().remove(user.email).apply()
    }
}