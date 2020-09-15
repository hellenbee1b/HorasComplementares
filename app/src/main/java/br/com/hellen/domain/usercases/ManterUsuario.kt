package br.com.hellen.domain.usercases

import br.com.hellen.data.models.UserModel
import br.com.hellen.domain.repositories.UserRepository

class ManterUsuario(private val repository: UserRepository) {

    fun addUser(nome:String, sobrenome:String, email:String, curso:String, senha:String): Boolean{
        if(nome.isNotBlank() && sobrenome.isNotBlank() && email.isNotBlank() && curso.isNotBlank() && senha.isNotBlank()) {
            repository.putUser(UserModel(nome, sobrenome, email, curso, senha))
            return true
        }
        return false
    }

    fun deleteUser(email: String){
        repository.deleteUser(UserModel(email = email))
    }
}