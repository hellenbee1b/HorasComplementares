package br.com.hellen.domain.usercases

import br.com.hellen.data.models.UserModel
import br.com.hellen.domain.repositories.UserRepository
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ManterUsuarioTest {
    @Mock
    private lateinit var repository: UserRepository
    private lateinit var manterUsuario: ManterUsuario

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        manterUsuario = ManterUsuario(repository)
    }

    @Test
    fun addUserValid() {
        // arranger
        val nome = "Teste"
        val sobrenome = "Teste"
        val email = "Teste"
        val curso = "Teste"
        val senha = "Teste"
        // act
        Mockito.`when`(this.repository.putUser(UserModel(nome, sobrenome, email, curso, senha))).thenAnswer{return@thenAnswer null }
        val result = manterUsuario.addUser(nome, sobrenome, email, curso, senha)
        // assert
        assertTrue(result)
    }

    @Test
    fun addUserFail() {
        // arranger
        val nome = ""
        val sobrenome = "Teste"
        val email = "Teste"
        val curso = "Teste"
        val senha = "Teste"
        // act
        Mockito.`when`(this.repository.putUser(UserModel(nome, sobrenome, email, curso, senha))).thenAnswer{return@thenAnswer null }
        val result = manterUsuario.addUser(nome, sobrenome, email, curso, senha)
        // assert
        assertFalse(result)
    }

    @Test
    fun deleteUser() {
    }
}