package br.com.hellen.horascomplementares

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Capturando o texto digitado da tela de login
        btnEntrar.setOnClickListener{
            val usuario = editUsuario.text.toString().trim()
            val senha = editSenha.text.toString().trim()

            //Se campo usuário estiver vazio
            if(usuario.isEmpty()){
                editUsuario.error = "Campo obrigatório!"
                editUsuario.requestFocus()
            }else if(senha.isEmpty()){
                editSenha.error = "Campo obrigatório!"
                editUsuario.requestFocus()
            }else if(usuario == "Hellen"){
                if(senha == "1234"){

                    //Apresentar uma mensagem para o usuário
                    Toast.makeText(this@LoginActivity, "Bem vinda minha querida desenvolvedora <3", Toast.LENGTH_LONG).show()
                    //Abrir a tela Main
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))

                    //Tirar a tela do empilhamento

                    finish()
                }else {
                    //Apresentar erro de senha
                    Toast.makeText(this@LoginActivity, "Senha incorreta", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@LoginActivity, "Usuario incorreto", Toast.LENGTH_LONG).show()
            }
        }
        //Clique no botão "Cadastrar"
        btnCadastrar.setOnClickListener{
            // Abrir a tela de cadastro
            startActivity(Intent(this@LoginActivity, RegisterUserActivity::class.java))
        }
    }
}
