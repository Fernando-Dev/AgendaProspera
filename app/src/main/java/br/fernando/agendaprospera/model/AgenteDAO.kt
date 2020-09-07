package br.fernando.agendaprospera.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_AGENTE_ID
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_AGENTE_LOGIN
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_AGENTE_NOME
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_AGENTE_SENHA
import br.fernando.agendaprospera.model.Constantes.Companion.TABELA_AGENTE
import br.fernando.agendaprospera.model.dominio.Agente

class AgenteDAO(context: Context) {

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

    fun salvarAgente(agente: Agente): Long {
//        ABRINDO BANCO DE DADOS
        val db = abrirBanco()
//        ENVIANDO DADOS PARA O BANCO
        val values = ContentValues().apply {
            put(COLUNA_AGENTE_NOME, agente.nome)
            put(COLUNA_AGENTE_LOGIN, agente.login)
            put(COLUNA_AGENTE_SENHA, agente.senha)
        }
//        FANEDO O INSERT NO BANCO DE DADOS
        return db.insert(TABELA_AGENTE, null, values)
    }

    fun editarAgente(agente: Agente): Long {
//        abrir banco de dados
        val db = abrirBanco()
//        preparando os dados par atualiacao no banco de dados
        val values = ContentValues().apply {
            put(COLUNA_AGENTE_ID, agente.id)
            put(COLUNA_AGENTE_NOME, agente.nome)
            put(COLUNA_AGENTE_LOGIN, agente.login)
            put(COLUNA_AGENTE_SENHA, agente.senha)
        }
//        argumento para comparar identificador
        val selecao = COLUNA_AGENTE_ID + " = ?"
//        argumento para que recebe o identificador do obejto
        val selecaoArgumento = arrayOf(agente.id.toString())
//        faendo a atualiacao dos dados no banco de dados segundo os argumentos
        return db.update(TABELA_AGENTE, values, selecao, selecaoArgumento).toLong()

    }

    fun buscarAgente(id: Int): Agente {
        //        GERANDO OBJETO AGENTE
        val agente = Agente()
//        lendo O BANCO DE DADOS
        val db = lerBanco()
//        SCRIPT PARA REALIAR A CONSULTA NO BANCO DE DADOS
        val selectQuery =
            "SELECT * FROM $TABELA_AGENTE WHERE $COLUNA_AGENTE_ID = ? $id"
//        CURSOR PARA BUSCAR O OBJETO NO BANCO DE DADOS
        val cursor = db.rawQuery(selectQuery, null)
        cursor?.moveToFirst()
        agente.id = cursor.getInt(cursor.getColumnIndex(COLUNA_AGENTE_ID))
        agente.nome = cursor.getString(cursor.getColumnIndex(COLUNA_AGENTE_NOME))
        agente.login = cursor.getString(cursor.getColumnIndex(COLUNA_AGENTE_LOGIN))
        agente.senha = cursor.getString(cursor.getColumnIndex(COLUNA_AGENTE_SENHA))
        cursor.close()
        return agente

    }

    fun listarAgente(): ArrayList<Agente> {
//        criando colecao do tipo do objeto
        val agenteLista = ArrayList<Agente>()
//        lendo o banco de dados
        val db = lerBanco()
//        argumento para consulta
        val selecaoArgumento = "SELECT * FROM $TABELA_AGENTE"
//        percorrendo banco de dados com o cursor
        val cursor = db.rawQuery(selecaoArgumento, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val agente = Agente()
//                    coletando dados
                    agente.id = cursor.getInt(cursor.getColumnIndex(COLUNA_AGENTE_ID))
                    agente.nome = cursor.getString(cursor.getColumnIndex(COLUNA_AGENTE_NOME))
                    agente.login = cursor.getString(cursor.getColumnIndex(COLUNA_AGENTE_LOGIN))
                    agente.senha = cursor.getString(cursor.getColumnIndex(COLUNA_AGENTE_SENHA))
//                    adicionando dados na colecao
                    agenteLista.add(agente)
                } while (cursor.moveToNext())
            }
        }
//        fecha cursor
        cursor.close()
//        retorna a lista de dados
        return agenteLista
    }

    fun excluirAgente(id: Int): Boolean {
//        abrindo o abrindo o banco de dados
        val db = abrirBanco()
//        argumento para comparacao de identificador
        val selecaoClausula = "$COLUNA_AGENTE_ID = ? $id"
//        argumento passado por parametro
        val argumento = arrayOf(id.toString())
//        removendo objeto do banco de dados
        val removido: Int = db.delete(TABELA_AGENTE, selecaoClausula, argumento)
        return removido > 0
    }

    fun iniciarSessao(login:String,senha:String):Boolean{
        val db = lerBanco()

//        falta terminar metodo
        return true

    }

}