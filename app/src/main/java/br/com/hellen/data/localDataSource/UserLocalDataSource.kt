package br.com.hellen.data.localDataSource

import android.content.SharedPreferences
import br.com.hellen.core.Either
import br.com.hellen.core.Either.Companion.left
import br.com.hellen.core.Either.Companion.right
import br.com.hellen.core.ResponseFail
import br.com.hellen.core.ResponseSuccess
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

    fun deleteUser(user:UserModel): Either<ResponseFail, ResponseSuccess> {
        sharedPreferences.getString(user.email, null) ?: return left(ResponseFail(message= "Conta inexistente"))
        sharedPreferences.edit().remove(user.email).apply()
        return right(ResponseSuccess(null, "Conta excluída da base de dados"))
    }

    fun addUser(user:UserModel){
        val userJson = Gson().toJson(user)
        sharedPreferences.edit().putString("users", userJson).apply()
    }
}