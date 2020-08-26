package br.fernando.agendaprospera.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CHAVE_ESTRANGEIRA_CLIENTE
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_QUILOMETRAGEM_DISTANCIA
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_QUILOMETRAGEM_ID
import br.fernando.agendaprospera.model.Constantes.Companion.TABELA_QUILOMETRAGEM
import br.fernando.agendaprospera.model.dominio.Quilometragem

class QuilometragemDAO(context: Context) {

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

    fun salvarQuilometragem(quilometragem: Quilometragem): Long {
//        ABRINDO BANCO DE DADOS
        val db = abrirBanco()
//        ENVIANDO DADOS PARA O BANCO
        val values = ContentValues().apply {
            put(COLUNA_QUILOMETRAGEM_DISTANCIA, quilometragem.distancia)
            put(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE, quilometragem.clienteId)
            put(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE, quilometragem.clienteAgenteId)
        }
//        FANEDO O INSERT NO BANCO DE DADOS
        return db.insert(TABELA_QUILOMETRAGEM, null, values)
    }

    fun editarQuilometragem(quilometragem: Quilometragem): Long {
//        abrir banco de dados
        val db = abrirBanco()
//        preparando os dados par atualiacao no banco de dados
        val values = ContentValues().apply {
            put(COLUNA_QUILOMETRAGEM_ID, quilometragem.id)
            put(COLUNA_QUILOMETRAGEM_DISTANCIA, quilometragem.distancia)
            put(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE, quilometragem.clienteId)
            put(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE, quilometragem.clienteAgenteId)
        }
//        argumento para comparar identificador
        val selecao = COLUNA_QUILOMETRAGEM_ID + " = ?"
//        argumento para que recebe o identificador do obejto
        val selecaoArgumento = arrayOf(quilometragem.id.toString())
//        faendo a atualiacao dos dados no banco de dados segundo os argumentos
        return db.update(TABELA_QUILOMETRAGEM, values, selecao, selecaoArgumento).toLong()

    }

    fun buscarQuilometragem(id: Int): Quilometragem {
        //        GERANDO OBJETO AGENTE
        val quilometragem = Quilometragem()
//        lendo O BANCO DE DADOS
        val db = lerBanco()
//        SCRIPT PARA REALIAR A CONSULTA NO BANCO DE DADOS
        val selectQuery =
            "SELECT * FROM $TABELA_QUILOMETRAGEM WHERE $COLUNA_QUILOMETRAGEM_ID = ? $id"
//        CURSOR PARA BUSCAR O OBJETO NO BANCO DE DADOS
        val cursor = db.rawQuery(selectQuery, null)
        cursor?.moveToFirst()
        quilometragem.id = cursor.getInt(cursor.getColumnIndex(COLUNA_QUILOMETRAGEM_ID))
        quilometragem.distancia = cursor.getInt(cursor.getColumnIndex(COLUNA_QUILOMETRAGEM_DISTANCIA))
        quilometragem.clienteId = cursor.getInt(cursor.getColumnIndex(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE))
        quilometragem.clienteAgenteId = cursor.getInt(cursor.getColumnIndex(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE))
        cursor.close()
        return quilometragem

    }

    fun listarQuilometragem(): ArrayList<Quilometragem> {
//        criando colecao do tipo do objeto
        val quilometragemLista = ArrayList<Quilometragem>()
//        lendo o banco de dados
        val db = lerBanco()
//        argumento para consulta
        val selecaoArgumento = "SELECT * FROM $TABELA_QUILOMETRAGEM"
//        percorrendo banco de dados com o cursor
        val cursor = db.rawQuery(selecaoArgumento, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val quilometragem = Quilometragem()
//                    coletando dados
                    quilometragem.id = cursor.getInt(cursor.getColumnIndex(COLUNA_QUILOMETRAGEM_ID))
                    quilometragem.distancia = cursor.getInt(cursor.getColumnIndex(COLUNA_QUILOMETRAGEM_DISTANCIA))
                    quilometragem.clienteId = cursor.getInt(cursor.getColumnIndex(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE))
                    quilometragem.clienteAgenteId =
                        cursor.getInt(cursor.getColumnIndex(COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE))
//                    adicionando dados na colecao
                    quilometragemLista.add(quilometragem)
                } while (cursor.moveToNext())
            }
        }
//        fecha cursor
        cursor.close()
//        retorna a lista de dados
        return quilometragemLista
    }

    fun excluirQuilometragem(id: Int): Boolean {
//        abrindo o abrindo o banco de dados
        val db = abrirBanco()
//        argumento para comparacao de identificador
        val selecaoClausula = "$COLUNA_QUILOMETRAGEM_ID = ? $id"
//        argumento passado por parametro
        val argumento = arrayOf(id.toString())
//        removendo objeto do banco de dados
        val removido: Int = db.delete(TABELA_QUILOMETRAGEM, selecaoClausula, argumento)
        return removido > 0
    }


}