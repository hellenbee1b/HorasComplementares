package br.com.hellen.horascomplementares

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register_task.*
import kotlinx.android.synthetic.main.activity_register_user.*
import kotlinx.android.synthetic.main.activity_register_user.btnCadastrar
import kotlinx.android.synthetic.main.activity_register_user.editCurso

class RegisterTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_task)
        //Criando a lista de sexo
        val listaCategoria = arrayOf( "Selecione a categoria",
        "Iniciação à docência",
        "Iniciação à pesquisa",
        "Extensão",
        "Artístico-culturais e esportivas",
        "Participação e/ou organização de eventos",
        "Experiências ligadas à formação profissional e/ou correlatas",
        "Produção Técnica e/ou Científica",
        "Vivências de gestão",
        "Outras atividades")
        //Adapter do Spinner
        val categoriaAdapter = ArrayAdapter(
            this@RegisterTaskActivity,
            android.R.layout.simple_spinner_dropdown_item,
            listaCategoria
        )

        //Adicionando o adapter ao spinner
        //spnSexo.adapter = sexoAdapter

        //Se o botão de cadastrar for clicado
        btnCadastrar.setOnClickListener {
            val curso = editCurso.text.toString().trim()
            val horasTotais = editHorasTotais.text.trim()
            val dataInicio = editDataInicio.text.toString().trim()
            val dataTermino = editDataTermino.text.toString().trim()
            val professor = editProfessor.text.toString().trim()
            val categoria = spnCategoria.selectedItem.toString()
            val objetivo = editObjetivo.text.toString().trim()
            val descricao = editDescricao.text.toString().trim()

            //Verificando se os campos estão preenchidos
            if (curso.isEmpty() ||
                horasTotais.isEmpty() ||
                dataInicio.isEmpty() ||
                dataTermino.isEmpty() ||
                professor.isEmpty() ||
                objetivo.isEmpty() ||
                descricao.isEmpty() ||
                categoria == "Selecione o sexo"){

                //Apresentando o erro ao usuário
                Toast.makeText(
                    this@RegisterTaskActivity,
                    "Todos os campos são obrigatórios",
                    Toast.LENGTH_LONG
                ).show()
            }else {
                //Apresentar mensagem de cadastro realizado
                Toast.makeText(
                    this@RegisterTaskActivity,
                    "Atividade cadastrada com sucesso",
                    Toast.LENGTH_LONG
                ).show()

                //Criando o shared preferencias
                val dados = getSharedPreferences(
                    "cadastro-$curso",
                    Context.MODE_PRIVATE
                )
                //Criando o editor de Shared Preferences
                //.apply{} - > Função Lâmbda : adicionar as informações no objeto
                //.apply() - > Salvar as informações no shared preferences

                dados.edit().apply{
                    putString("curso", curso)
                  //  putInt("horasTotais", horasTotais)
                    putString("dataInicio", dataInicio)
                    putString("dataTermino", dataTermino)
                    putString("professor", professor)
                    putString("categoria", categoria)
                    putString("objetivo", objetivo)
                    putString("descricao", descricao)

                }.apply()

                //Abrir a tela main e passando parâmetros
                startActivity(
                    Intent(this@RegisterTaskActivity, MainActivity::class.java).apply {
                        putExtra("Atividade", curso)
                    }
                )
                //Tirar todas as telas do empilhamento
                finishAffinity()

            }
        }
    }
}
