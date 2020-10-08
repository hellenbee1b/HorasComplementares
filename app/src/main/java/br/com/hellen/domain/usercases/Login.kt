package br.com.hellen.domain.usercases

import br.com.hellen.core.Either
import br.com.hellen.core.Either.Companion.left
import br.com.hellen.core.Either.Companion.right
import br.com.hellen.core.ResponseFail
import br.com.hellen.core.ResponseSuccess
import br.com.hellen.domain.entities.User
import br.com.hellen.domain.repositories.UserRepository

class Login(private val repository: UserRepository) {

    fun userApproval(username:String, password:String): Either<ResponseFail, ResponseSuccess> {
        if(username.isEmpty() || password.isEmpty())
            return left(ResponseFail(message="Campos obrigatórios"))
        val response = repository.getUser(username)
        if(response is ResponseSuccess) {
            val user: User = response.data as User
            return when(user.senha == password) {
                true -> right(response.apply { message = "Usuário localizado" })
                false -> left(ResponseFail(message = "Acesso negado"))
            }
        }
        return left(ResponseFail(message="Usuário não encontrado"))
    }
}