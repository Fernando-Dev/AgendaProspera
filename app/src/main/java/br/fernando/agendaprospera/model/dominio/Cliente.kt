package br.fernando.agendaprospera.model.dominio

data class Cliente (
    var id:Int, var nome:String, var apelido: String,
    var rua:String, var numero:Int, var complemento:String,
    var bairro:String, var cidade:String, var telefone:String,
    var longitude:String, var latitude:String, var atividade:String,
    var horario:String, var data:String, var agenteId:Int
) {
    constructor() : this(
        0,"","","",0,"","",
        "","","","","","","",
        0
    )
}

