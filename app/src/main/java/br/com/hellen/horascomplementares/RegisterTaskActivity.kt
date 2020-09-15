package br.com.hellen.horascomplementares

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.hellen.data.localDataSource.AtividadeLocalDataSource
import br.com.hellen.data.repositories.AtividadeRepositoryImp
import br.com.hellen.domain.usercases.ManterAtividade
import kotlinx.android.synthetic.main.activity_register_task.*
import kotlinx.android.synthetic.main.activity_register_user.btnCadastrar
import kotlinx.android.synthetic.main.activity_register_user.editCurso
import java.sql.Date

class RegisterTaskActivity : AppCompatActivity() {

    private lateinit var manterAtividade: ManterAtividade

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

        manterAtividade = ManterAtividade(AtividadeRepositoryImp(AtividadeLocalDataSource(getSharedPreferences("atividades", Context.MODE_PRIVATE))))

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
            if(manterAtividade.addAtividade(curso, Integer.parseInt(horasTotais.toString()), Date.valueOf(dataInicio), Date.valueOf(dataTermino), professor, categoria, objetivo, descricao)){
                Toast.makeText(this@RegisterTaskActivity, "Atividade cadastrada com sucesso", Toast.LENGTH_LONG).show()
                startActivity(
                    Intent(this@RegisterTaskActivity, MainActivity::class.java).apply {
                        putExtra("Atividade", curso)
                    }
                )
                finishAffinity()
            }else {
                //Apresentando o erro ao usuário
                Toast.makeText(this@RegisterTaskActivity, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show()
            }
        }
    }
}
