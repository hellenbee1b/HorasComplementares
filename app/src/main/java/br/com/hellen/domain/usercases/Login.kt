package br.com.hellen.domain.usercases

import br.com.hellen.core.ResponseSuccess
import br.com.hellen.domain.entities.User
import br.com.hellen.domain.repositories.UserRepository

class Login(private val repository: UserRepository) {

    fun userApproval(username:String, password:String): Boolean{
        if(username.isEmpty() || password.isEmpty())
            return false
        val response = repository.getUser(username)
        if(response is ResponseSuccess) {
            val user: User = response.data as User
            return user.senha == password
        }
        return false
    }
}