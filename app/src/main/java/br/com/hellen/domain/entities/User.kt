package br.com.hellen.domain.entities

open class User (
    var nome:String = "",
    var sobrenome:String = "",
    var curso:String = "",
    var email:String ="",
    var senha:String = ""
) {
    override fun toString(): String = "User(nome: $nome, sobrenome: $sobrenome, curso: $curso, email: $email, senha: $senha)"
}