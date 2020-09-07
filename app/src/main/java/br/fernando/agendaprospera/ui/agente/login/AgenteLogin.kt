package br.fernando.agendaprospera.ui.agente.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.fernando.agendaprospera.R

class AgenteLogin : AppCompatActivity(), AgenteLoginContrato.View {

    private lateinit var agenteLoginPresenter: AgenteLoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agente_login)

        agenteLoginPresenter = AgenteLoginPresenter(this)

    }
}
