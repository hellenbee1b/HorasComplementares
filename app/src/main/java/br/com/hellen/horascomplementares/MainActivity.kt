package br.com.hellen.horascomplementares

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCadastrarAtividade.setOnClickListener {
            // Abrir a tela de cadastro de atividades
            startActivity(Intent(this@MainActivity, RegisterTaskActivity::class.java))
        }
    }
}
