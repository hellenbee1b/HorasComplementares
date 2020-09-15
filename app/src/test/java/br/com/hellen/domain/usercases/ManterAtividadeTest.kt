package br.com.hellen.domain.usercases

import br.com.hellen.data.models.AtividadeModel
import br.com.hellen.domain.repositories.AtividadeRepository
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.sql.Date

class ManterAtividadeTest {
    @Mock
    private lateinit var repository: AtividadeRepository
    private lateinit var manterAtividade: ManterAtividade

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        manterAtividade = ManterAtividade(repository)
    }

    @Test
    fun addAtividadeValid() {
        // arranger
        val curso = "Test"
        val horasTotais = 10
        val dataInicio = Date(java.util.Date().time)
        val dataTermino = Date(java.util.Date().time)
        val professor = "Test"
        val categoria = "Test"
        val objetivo = "Test"
        val descricao = "Test"
        // act
        Mockito.`when`(this.repository.putAtividade(AtividadeModel(curso, horasTotais, dataInicio, dataTermino, professor, categoria, objetivo, descricao))).thenAnswer{return@thenAnswer null }
        val result = manterAtividade.addAtividade(curso, Integer.parseInt(horasTotais.toString()), dataInicio, dataTermino, professor, categoria, objetivo, descricao)
        // assert
        assertTrue(result)
    }

    @Test
    fun addAtividadeFail() {
        // arranger
        val curso = ""
        val horasTotais = 0
        val dataInicio = Date(java.util.Date().time)
        val dataTermino = Date(java.util.Date().time)
        val professor = "Test"
        val categoria = "Test"
        val objetivo = "Test"
        val descricao = "Test"
        // act
        Mockito.`when`(this.repository.putAtividade(AtividadeModel(curso, horasTotais, dataInicio, dataTermino, professor, categoria, objetivo, descricao))).thenAnswer{return@thenAnswer null }
        val result = manterAtividade.addAtividade(curso, Integer.parseInt(horasTotais.toString()), dataInicio, dataTermino, professor, categoria, objetivo, descricao)
        // assert
        assertFalse(result)
    }

    @Test
    fun deleteAtividade() {
    }
}