package br.com.hellen.domain.repositories

import br.com.hellen.core.Either
import br.com.hellen.core.Response
import br.com.hellen.core.ResponseFail
import br.com.hellen.core.ResponseSuccess
import br.com.hellen.data.models.UserModel

interface UserRepository {
    fun getUser(username:String): Response
    fun putUser(user:UserModel)
    fun deleteUser(user:UserModel): Either<ResponseFail, ResponseSuccess>
    fun addUser(user:UserModel)
}