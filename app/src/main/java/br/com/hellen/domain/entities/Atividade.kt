package br.com.hellen.domain.entities

import java.sql.Date

open class Atividade(
    val curso:String = "",
    val horasTotais:Int = 0,
    val dataInicio: Date? = null,
    val dataTermino: Date? = null,
    val professor: String = "",
    val categoria: String = "",
    val objetivo: String = "",
    val descricao: String = ""
)