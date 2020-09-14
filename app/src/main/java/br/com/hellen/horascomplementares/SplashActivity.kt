package br.com.hellen.horascomplementares

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Executar uma ação após determinado tempo
        Handler().postDelayed({
            val mIntent = Intent(this, LoginActivity::class.java)
            startActivity(mIntent)
            //Remove a activity
            finish()

        },5000)
    }
}
