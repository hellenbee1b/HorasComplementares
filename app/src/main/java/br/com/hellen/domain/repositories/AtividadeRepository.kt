package br.com.hellen.domain.repositories

import br.com.hellen.core.Response
import br.com.hellen.data.models.AtividadeModel

interface AtividadeRepository {
    fun getAtividade(curso:String): Response
    fun putAtividade(atividade: AtividadeModel)
    fun deleteAtividade(atividade:AtividadeModel)
}