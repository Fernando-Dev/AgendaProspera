package br.fernando.agendaprospera.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_AGENDA_ID
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_AGENDA_VALOR_ABASTECIDO
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CHAVE_ESTRANGEIRA_CLIENTE
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE
import br.fernando.agendaprospera.model.Constantes.Companion.TABELA_AGENDA
import br.fernando.agendaprospera.model.dominio.Agenda

class AgendaDAO(context: Context) {

    val repositorio = Repositorio(context)
    var db: SQLiteDatabase = TODO()

    /**
     * funcao para abrir o banco de dados
     */
    fun abrirBanco(): SQLiteDatabase {
        if (db == null) {
            db = repositorio.writableDatabase
        }
        return db
    }

    /**
     * funcao para ler o banco de dados
     */

    fun lerBanco(): SQLiteDatabase {
        if (db == null) {
            db = repositorio.readableDatabase
        }
        return db
    }

    /**
     * funcao para fechar o banco de dados
     */

    fun fecharBanco() {
        repositorio.close()
    }

    fun salvarAgenda(agenda: Agenda): Long {
//        ABRINDO BANCO DE DADOS
        val db = abrirBanco()
//        ENVIANDO DADOS PARA O BANCO
        val values = ContentValues().apply {
            put(COLUNA_AGENDA_VALOR_ABASTECIDO, agenda.valorAbastecido)
            put(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE, agenda.clienteId)
            put(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE, agenda.clienteAgenteId)
        }
//        FANEDO O INSERT NO BANCO DE DADOS
        return db.insert(TABELA_AGENDA, null, values)
    }

    fun editarAgenda(agenda: Agenda): Long {
//        abrir banco de dados
        val db = abrirBanco()
//        preparando os dados par atualiacao no banco de dados
        val values = ContentValues().apply {
            put(COLUNA_AGENDA_ID, agenda.id)
            put(COLUNA_AGENDA_VALOR_ABASTECIDO, agenda.valorAbastecido)
            put(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE, agenda.clienteId)
            put(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE, agenda.clienteAgenteId)
        }
//        argumento para comparar identificador
        val selecao = COLUNA_AGENDA_ID + " = ?"
//        argumento para que recebe o identificador do obejto
        val selecaoArgumento = arrayOf(agenda.id.toString())
//        faendo a atualiacao dos dados no banco de dados segundo os argumentos
        return db.update(TABELA_AGENDA, values, selecao, selecaoArgumento).toLong()

    }

    fun buscarAgenda(id: Int): Agenda {
        //        GERANDO OBJETO AGENTE
        val agenda = Agenda()
//        lendo O BANCO DE DADOS
        val db = lerBanco()
//        SCRIPT PARA REALIAR A CONSULTA NO BANCO DE DADOS
        val selectQuery =
            "SELECT * FROM $TABELA_AGENDA WHERE $COLUNA_AGENDA_ID = ? $id"
//        CURSOR PARA BUSCAR O OBJETO NO BANCO DE DADOS
        val cursor = db.rawQuery(selectQuery, null)
        cursor?.moveToFirst()
        agenda.id = cursor.getInt(cursor.getColumnIndex(COLUNA_AGENDA_ID))
        agenda.valorAbastecido = cursor.getDouble(cursor.getColumnIndex(COLUNA_AGENDA_VALOR_ABASTECIDO))
        agenda.clienteId = cursor.getInt(cursor.getColumnIndex(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE))
        agenda.clienteAgenteId = cursor.getInt(cursor.getColumnIndex(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE))
        cursor.close()
        return agenda

    }

    fun listarAgenda(): ArrayList<Agenda> {
//        criando colecao do tipo do objeto
        val agendaLista = ArrayList<Agenda>()
//        lendo o banco de dados
        val db = lerBanco()
//        argumento para consulta
        val selecaoArgumento = "SELECT * FROM $TABELA_AGENDA"
//        percorrendo banco de dados com o cursor
        val cursor = db.rawQuery(selecaoArgumento, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val agenda = Agenda()
//                    coletando dados
                    agenda.id = cursor.getInt(cursor.getColumnIndex(COLUNA_AGENDA_ID))
                    agenda.valorAbastecido = cursor.getDouble(cursor.getColumnIndex(COLUNA_AGENDA_VALOR_ABASTECIDO))
                    agenda.clienteId = cursor.getInt(cursor.getColumnIndex(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE))
                    agenda.clienteAgenteId =
                        cursor.getInt(cursor.getColumnIndex(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE))
//                    adicionando dados na colecao
                    agendaLista.add(agenda)
                } while (cursor.moveToNext())
            }
        }
//        fecha cursor
        cursor.close()
//        retorna a lista de dados
        return agendaLista
    }

    fun excluirAgenda(id: Int): Boolean {
//        abrindo o abrindo o banco de dados
        val db = abrirBanco()
//        argumento para comparacao de identificador
        val selecaoClausula = "$COLUNA_AGENDA_ID = ? $id"
//        argumento passado por parametro
        val argumento = arrayOf(id.toString())
//        removendo objeto do banco de dados
        val removido: Int = db.delete(TABELA_AGENDA, selecaoClausula, argumento)
        return removido > 0
    }


}