package br.fernando.agendaprospera.model.dominio

/**
 * classe com os atributos do objeto quilometragem
 */
data class Quilometragem (
    var id:Int, var distancia:Int, var clienteId:Int, var clienteAgenteId:Int
) {
    constructor() : this(
        0,0,0,0
    )
}