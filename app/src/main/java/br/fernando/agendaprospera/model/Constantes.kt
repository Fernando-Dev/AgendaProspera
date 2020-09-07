package br.fernando.agendaprospera.model

class Constantes {
    /**
     * tabela serao agrupadas em objetos anonimos
     */
    companion object {
        /**
         * dados do banco de dados
         */

        const val DB_NOME = "agenda_prospera"
        const val DB_VERSAO = 2
        /**
         * colunas da tabela agente
         */
        const val TABELA_AGENTE = "agente"
        const val COLUNA_AGENTE_ID = "id"
        const val COLUNA_AGENTE_NOME = "nome"
        const val COLUNA_AGENTE_LOGIN = "login"
        const val COLUNA_AGENTE_SENHA = "senha"

        /**
         * colunas da taela cliente
         */
        const val TABELA_CLIENTE = "cliente"
        const val COLUNA_CLIENTE_ID = "id"
        const val COLUNA_CLIENTE_NOME = "nome"
        const val COLUNA_CLIENTE_APELIDO = "apelido"
        const val COLUNA_CLIENTE_RUA = "rua"
        const val COLUNA_CLIENTE_NUMERO = "numero"
        const val COLUNA_CLIENTE_COMPLEMENTO = "complemento"
        const val COLUNA_CLIENTE_BAIRRO = "bairro"
        const val COLUNA_CLIENTE_CIDADE = "cidade"
        const val COLUNA_CLIENTE_TELEFONE = "telefone"
        const val COLUNA_CLIENTE_LONGITUDE = "longitude"
        const val COLUNA_CLIENTE_LATITUDE = "latitude"
        const val COLUNA_CLIENTE_ATIVIDADE = "atividade"
        const val COLUNA_CLIENTE_HORARIO = "horario"
        const val COLUNA_CLIENTE_DATA = "data"
        const val COLUNA_CHAVE_ESTRANGEIRA_AGENTE = "agente_id"

        /**
         * colunas da tabela agenda
         *
         */

        const val TABELA_AGENDA = "agenda"
        const val COLUNA_AGENDA_ID = "id"
        const val COLUNA_AGENDA_VALOR_ABASTECIDO = "valor_abastecido"
        const val COLUNA_CHAVE_ESTRANGEIRA_CLIENTE = "cliente_id"
        const val COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE="cliente_agente_id"


    }


}