package br.com.hellen.domain.usercases

import br.com.hellen.core.Either.Companion.left
import br.com.hellen.core.Either.Companion.right
import br.com.hellen.core.ResponseFail
import br.com.hellen.core.ResponseSuccess
import br.com.hellen.data.models.UserModel
import br.com.hellen.domain.repositories.UserRepository
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ManterContaTest {
    @Mock
    private lateinit var repository: UserRepository
    private lateinit var manterUsuario: ManterUsuario

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = Mockito.mock(this.repository::class.java)
        manterUsuario = ManterUsuario(repository)
    }

    @Test
    fun addUserValid() {
        // arranger
        val nome = "Hellen"
        val sobrenome = "Maria"
        val email = "hellen@alu.ufc.br"
        val curso = "Ciência da computação"
        val senha = "1234"
        // act
        Mockito.`when`(this.repository.addUser(UserModel(nome, sobrenome, email, curso, senha))).thenAnswer{return@thenAnswer null }
        val result = manterUsuario.addUser(nome, sobrenome, email, curso, senha).getValue<ResponseSuccess>()
        // assert
        assertEquals(result, ResponseSuccess(null, "Cadastrado com sucesso"))
    }

    @Test
    fun addUserFail() {
        // arranger
        val nome = "Hellen"
        val sobrenome = "Maria"
        val email = "hellen@alu.ufc.br"
        val curso = "Ciência da computação"
        val senha = ""
        // act
        Mockito.`when`(this.repository.addUser(UserModel(nome, sobrenome, email, curso, senha))).thenAnswer{return@thenAnswer null }
        val result = manterUsuario.addUser(nome, sobrenome, email, curso, senha).getValue<ResponseFail>()
        // assert
        assertEquals(result, ResponseFail(null, "Campos obrigatório"))
    }

    @Test
    fun atualizarUserValid() {
        // arranger
        val nome = "Hellen"
        val sobrenome = "Maria"
        val email = "hellen@alu.ufc.br"
        val curso = "Ciência da computação"
        val senha = "1234"
        // act
        Mockito.`when`(this.repository.putUser(UserModel(nome, sobrenome, email, curso, senha))).thenAnswer{return@thenAnswer null }
        val result = manterUsuario.putUser(nome, sobrenome, email, curso, senha).getValue<ResponseSuccess>()
        // assert
        assertEquals(result, ResponseSuccess(null, "Alterado com sucesso"))
    }

    @Test
    fun atualizarUserFail() {
        // arranger
        val nome = "Hellen"
        val sobrenome = "Maria"
        val email = "hellen@alu.ufc.br"
        val curso = "Ciência da computação"
        val senha = ""
        // act
        Mockito.`when`(this.repository.putUser(UserModel(nome, sobrenome, email, curso, senha))).thenAnswer{return@thenAnswer null }
        val result = manterUsuario.putUser(nome, sobrenome, email, curso, senha).getValue<ResponseFail>()
        // assert
        assertEquals(result, ResponseFail(null, "Campos obrigatório"))
    }

    @Test(expected = AssertionError::class)
    fun deleteUserSuccess() {
        // arranger
        val email = "hellen@alu.ufc.br"
        // act
        Mockito.`when`(this.repository.deleteUser(UserModel(email = email)))
            .thenAnswer{right(ResponseSuccess(null, "Conta excluída da base de dados")) }
        val result = manterUsuario.deleteUser(email)
        // assert
        assertEquals(result, ResponseSuccess(null, "Conta excluída da base de dados"))
    }

    @Test
    fun deleteUserFildEmpty() {
        // arranger
        val email = ""
        // act
        val result = manterUsuario.deleteUser(email).getValue<ResponseFail>()
        // assert
        assertEquals(result, ResponseFail(message = "Operação inválida"))
    }

    @Test(expected = AssertionError::class)
    fun deleteUserFail() {
        // arranger
        val email = "neto@gmail.com"
        // act
        Mockito.`when`(this.repository.deleteUser(UserModel(email = email)))
            .thenAnswer{left(ResponseFail(message = "Conta inexistente"))}
        val result = manterUsuario.deleteUser(email)
        // assert
        assertEquals(result, left(ResponseFail(message = "Conta inexistente")))
    }
}