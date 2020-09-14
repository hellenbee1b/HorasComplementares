package br.com.hellen.horascomplementares

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        //Se o botão de cadastrar for clicado
        btnCadastrar.setOnClickListener {
            val nome = editNome.text.toString().trim()
            val sobrenome = editSobrenome.text.toString().trim()
            val email = editEmail.text.toString().trim().toLowerCase()
            val curso = editCurso.text.toString().trim()
            val senha = editSenha.text.toString().trim()
            val confSenha = editConfSenha.text.toString().trim()
            // val sexo = spnSexo.selectedItem.toString()

            //Verificando se os campos estão preenchidos
            if (nome.isEmpty() ||
                sobrenome.isEmpty() ||
                email.isEmpty() ||
                curso.isEmpty() ||
                senha.isEmpty() ||
                confSenha.isEmpty()){
                //  sexo == "Selecione o sexo"){

                //Apresentando o erro ao usuário
                Toast.makeText(
                    this@RegisterUserActivity,
                    "Todos os campos são obrigatórios",
                    Toast.LENGTH_LONG
                ).show()
            }else if(senha != confSenha){
                Toast.makeText(this@RegisterUserActivity, "Senhas não coincidem", Toast.LENGTH_LONG).show()
            }else {
                //Apresentar mensagem de cadastro realizado
                Toast.makeText(
                    this@RegisterUserActivity,
                    "Usuário cadastrado com sucesso",
                    Toast.LENGTH_LONG
                ).show()

                //Criando o shared preferencias
                val dados = getSharedPreferences(
                    "cadastro-$email",
                    Context.MODE_PRIVATE
                )
                //Criando o editor de Shared Preferences
                //.apply{} - > Função Lâmbda : adicionar as informações no objeto
                //.apply() - > Salvar as informações no shared preferences

                dados.edit().apply{
                    putString("nome", nome)
                    putString("sobrenome", sobrenome)
                    putString("curso", curso)
                    putString("email", email)
                    putString("senha", senha)
                    // putString("sexo", sexo)
                }.apply()

                //Abrir a tela main e passando parâmetros
                startActivity(
                    Intent(this@RegisterUserActivity, MainActivity::class.java).apply {
                        putExtra("Usuario", email)
                    }
                )
                //Tirar todas as telas do empilhamento
                finishAffinity()

            }
        }
    }
}
