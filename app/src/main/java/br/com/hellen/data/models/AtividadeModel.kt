package br.com.hellen.data.models

import br.com.hellen.domain.entities.Atividade
import java.sql.Date

class AtividadeModel(curso:String = "",
                     horasTotais:Int = 0,
                     dataInicio: Date? = null,
                     dataTermino: Date? = null,
                     professor: String = "",
                     categoria: String = "",
                     objetivo: String = "",
                     descricao: String = "" ): Atividade(curso, horasTotais, dataInicio, dataTermino, professor, categoria, objetivo, descricao)