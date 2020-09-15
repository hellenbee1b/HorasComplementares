package br.com.hellen.data.models

import br.com.hellen.domain.entities.User

class UserModel(nome:String = "", sobrenome:String = "", email:String = "", curso:String = "", senha:String = ""): User(nome, sobrenome, curso, email, senha)