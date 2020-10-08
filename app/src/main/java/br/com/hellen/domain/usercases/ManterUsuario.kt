package br.com.hellen.domain.usercases

import br.com.hellen.core.Either
import br.com.hellen.core.Either.Companion.left
import br.com.hellen.core.Either.Companion.right
import br.com.hellen.core.ResponseFail
import br.com.hellen.core.ResponseSuccess
import br.com.hellen.data.models.UserModel
import br.com.hellen.domain.repositories.UserRepository

class ManterUsuario(private val repository: UserRepository) {

    fun addUser(nome:String, sobrenome:String, email:String, curso:String, senha:String): Either<ResponseFail, ResponseSuccess> {
        if(nome.isNotBlank() && sobrenome.isNotBlank() && email.isNotBlank() && curso.isNotBlank() && senha.isNotBlank()) {
            repository.addUser(UserModel(nome, sobrenome, email, curso, senha))
            return right(ResponseSuccess(null, "Cadastrado com sucesso"))
        }
        return left(ResponseFail(message = "Campos obrigatório"))
    }

    fun deleteUser(email: String): Either<ResponseFail, ResponseSuccess>{
        if(email.isNotBlank())
            return repository.deleteUser(UserModel(email = email))
        return left(ResponseFail(message = "Operação inválida"))
    }

    fun putUser(nome:String, sobrenome:String, email:String, curso:String, senha:String): Either<ResponseFail, ResponseSuccess> {
        if (nome.isNotBlank() && sobrenome.isNotBlank() && email.isNotBlank() && curso.isNotBlank() && senha.isNotBlank()) {
            repository.putUser(UserModel(nome, sobrenome, email, curso, senha))
            return right(ResponseSuccess(null, "Alterado com sucesso"))
        }
        return left(ResponseFail(message = "Campos obrigatório"))
    }
}