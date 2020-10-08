package br.com.hellen.horascomplementares

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.hellen.data.localDataSource.UserLocalDataSource
import br.com.hellen.data.repositories.UserRepositoryImp
import br.com.hellen.domain.usercases.ManterUsuario
import kotlinx.android.synthetic.main.activity_register_user.*
import java.util.*

class RegisterUserActivity : AppCompatActivity() {
    private lateinit var manterUsuario: ManterUsuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        manterUsuario = ManterUsuario(UserRepositoryImp(UserLocalDataSource(getSharedPreferences("users", Context.MODE_PRIVATE))))

        //Se o botão de cadastrar for clicado
        btnCadastrar.setOnClickListener {
            val nome = editNome.text.toString().trim()
            val sobrenome = editSobrenome.text.toString().trim()
            val email = editEmail.text.toString().trim().toLowerCase(Locale.ROOT)
            val curso = editCurso.text.toString().trim()
            val senha = editSenha.text.toString().trim()

            //Verificando se os campos estão preenchidos
            manterUsuario.addUser(nome, sobrenome, email, curso, senha).fold( {
                Toast.makeText(this@RegisterUserActivity, it.message, Toast.LENGTH_LONG).show()
                //Abrir a tela main e passando parâmetros
                startActivity(
                    Intent(this@RegisterUserActivity, MainActivity::class.java).apply {
                        putExtra("Usuario", email)
                    }
                )
                //Tirar todas as telas do empilhamento
                finishAffinity()
            },{Toast.makeText(this@RegisterUserActivity, it.message, Toast.LENGTH_LONG).show()})
        }
    }
}
