package br.fernando.agendaprospera.model.dominio

/**
 * classe com os atributos do objeto quilometragem
 */
data class Agenda(
    var id: Int, var valorAbastecido: Double, var clienteId: Int, var clienteAgenteId: Int
) {
    constructor() : this(
        0, 0.0, 0, 0
    )
}