package br.fernando.agendaprospera.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.fernando.agendaprospera.R
import br.fernando.agendaprospera.ui.agente.login.AgenteLogin

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        objeto do tipo handler
        val handler = Handler()
//        fazendo delay na thread paralela
//        para chamar na principal a tela de login
        handler.postDelayed(Runnable {
            exibirLogin()
        },2000)

    }

    private fun exibirLogin() {
//        chamando a tela de login
        startActivity(Intent(this, AgenteLogin::class.java))
//        fecha actvities
        finish()

    }
}


