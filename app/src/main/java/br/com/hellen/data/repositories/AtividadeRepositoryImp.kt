package br.com.hellen.data.repositories

import br.com.hellen.core.Response
import br.com.hellen.core.ResponseFail
import br.com.hellen.core.ResponseSuccess
import br.com.hellen.data.localDataSource.AtividadeLocalDataSource
import br.com.hellen.data.models.AtividadeModel
import br.com.hellen.domain.repositories.AtividadeRepository

class AtividadeRepositoryImp(private val localDataSource: AtividadeLocalDataSource): AtividadeRepository {

    override fun getAtividade(curso: String): Response {
        val result = localDataSource.getAtividade(curso)
        return if(result != null) ResponseSuccess(result, "Atividade localizada") else ResponseFail(message = "Atividade não localizado")
    }

    override fun putAtividade(atividade: AtividadeModel) {
        localDataSource.putAtividade(atividade)
    }

    override fun deleteAtividade(atividade: AtividadeModel) {
        localDataSource.deleteAtividade(atividade)
    }

}