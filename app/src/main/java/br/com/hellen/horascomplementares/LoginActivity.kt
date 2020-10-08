package br.com.hellen.horascomplementares

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.hellen.data.localDataSource.UserLocalDataSource
import br.com.hellen.data.repositories.UserRepositoryImp
import br.com.hellen.domain.usercases.Login
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var login: Login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login = Login(UserRepositoryImp(UserLocalDataSource(getSharedPreferences("users", Context.MODE_PRIVATE))))

        //Capturando o texto digitado da tela de login
        btnEntrar.setOnClickListener{
            val usuario = editUsuario.text.toString().trim()
            val senha = editSenha.text.toString().trim()
            val result = login.userApproval(usuario, senha)
            result.fold<Any>(
                {Toast.makeText(this@LoginActivity, it.toString(), Toast.LENGTH_LONG).show()},
                {startActivity(Intent(this@LoginActivity, MainActivity::class.java))}
            )
        }
        //Clique no botão "Cadastrar"
        btnCadastrar.setOnClickListener{
            // Abrir a tela de cadastro
            startActivity(Intent(this@LoginActivity, RegisterUserActivity::class.java))
        }
    }
}
