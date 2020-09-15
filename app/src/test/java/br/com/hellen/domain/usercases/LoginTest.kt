package br.com.hellen.domain.usercases

import br.com.hellen.core.ResponseFail
import br.com.hellen.core.ResponseSuccess
import br.com.hellen.domain.entities.User
import br.com.hellen.domain.repositories.UserRepository
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.mockito.*

class LoginTest {
    @Mock
    private lateinit var repository: UserRepository
    private lateinit var login: Login

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        login = Login(repository)
    }

    @Test
    fun loginCreate(){
        // assert
        Assert.assertNotNull(login)
    }

    @Test
    fun loginApprovalFail() {
        // arrange
        val username = "111111"
        val password = "111111"
        // act
        Mockito.`when`(this.repository.getUser(ArgumentMatchers.anyString())).thenAnswer{return@thenAnswer ResponseFail(message = "Usuário não encontrado") }
        val result = login.userApproval(username, password)
        // assert
        Assert.assertFalse(result)
    }

    @Test
    fun userApprovalValid() {
        // arrange
        val username = "Hellen"
        val password = "1234"
        // act
        Mockito.`when`(this.repository.getUser(ArgumentMatchers.anyString())).thenAnswer{return@thenAnswer ResponseSuccess(User(email = "Hellen",senha = "1234"), "Usuário localizado") }
        val result = login.userApproval(username, password)
        // assert
        Assert.assertTrue(result)
    }
}