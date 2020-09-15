package br.com.hellen.domain.usercases

import br.com.hellen.data.models.AtividadeModel
import br.com.hellen.domain.repositories.AtividadeRepository
import java.sql.Date

class ManterAtividade(private val repository: AtividadeRepository) {

    fun addAtividade(curso:String, horasTotais:Int, dataInicio: Date?, dataTermino: Date?, professor: String, categoria: String, objetivo: String, descricao: String): Boolean{
        if(curso.isNotBlank() && horasTotais > 0 && dataInicio != null && dataTermino != null && professor.isNotBlank() && categoria.isNotBlank() && objetivo.isNotBlank() && descricao.isNotBlank()) {
            repository.putAtividade(AtividadeModel(curso, horasTotais, dataInicio, dataTermino, professor, categoria, objetivo))
            return true
        }
        return false
    }

    fun deleteAtividade(curso: String){
        repository.deleteAtividade(AtividadeModel(curso = curso))
    }
}