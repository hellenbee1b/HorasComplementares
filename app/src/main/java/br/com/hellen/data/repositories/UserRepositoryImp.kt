package br.com.hellen.data.repositories

import br.com.hellen.core.Response
import br.com.hellen.core.ResponseFail
import br.com.hellen.core.ResponseSuccess
import br.com.hellen.data.localDataSource.UserLocalDataSource
import br.com.hellen.data.models.UserModel
import br.com.hellen.domain.repositories.UserRepository

class UserRepositoryImp(private val localDataSource: UserLocalDataSource): UserRepository {

    override fun getUser(username: String): Response {
        val result = localDataSource.getUser(username)
        return if(result != null) ResponseSuccess(result, "Usuário localizado") else ResponseFail(message = "Usuário não localizado")
    }

    override fun putUser(user: UserModel) {
        localDataSource.putUser(user)
    }

    override fun deleteUser(user: UserModel) {
        localDataSource.deleteUser(user)
    }

}