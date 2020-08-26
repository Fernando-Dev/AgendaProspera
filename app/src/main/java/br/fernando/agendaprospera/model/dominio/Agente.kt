package br.fernando.agendaprospera.model.dominio

data class Agente(
    var id: Int, var nome: String, var login: String, var senha: String
) {
    constructor() : this(0,"","","")

}