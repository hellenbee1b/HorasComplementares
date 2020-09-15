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
            val confSenha = editConfSenha.text.toString().trim()

            //Verificando se os campos estão preenchidos
            if(senha == confSenha){
                if(manterUsuario.addUser(nome, sobrenome, email, curso, senha)) {
                    Toast.makeText(this@RegisterUserActivity, "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show()
                    //Abrir a tela main e passando parâmetros
                    startActivity(
                        Intent(this@RegisterUserActivity, MainActivity::class.java).apply {
                            putExtra("Usuario", email)
                        }
                    )
                    //Tirar todas as telas do empilhamento
                    finishAffinity()
                }
            } else {
                Toast.makeText(this@RegisterUserActivity, "Senhas não coincidem", Toast.LENGTH_LONG).show()
            }
        }
    }
}
