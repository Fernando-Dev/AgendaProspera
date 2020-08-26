package br.fernando.agendaprospera.model.dominio

data class Cliente (
    val id:Int, val nome:String, val apelido: String,
    val rua:String, val numero:Int, val complemento:String,
    val bairro:String, val cidade:String, val telefone:String,
    val longitude:String, val latitude:String, val atividade:String,
    val horario:String, val data:String
)
