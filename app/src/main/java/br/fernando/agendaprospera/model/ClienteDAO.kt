package br.fernando.agendaprospera.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CHAVE_ESTRANGEIRA_AGENTE
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_APELIDO
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_ATIVIDADE
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_BAIRRO
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_CIDADE
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_COMPLEMENTO
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_DATA
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_HORARIO
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_ID
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_LATITUDE
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_LONGITUDE
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_NOME
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_NUMERO
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_RUA
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CLIENTE_TELEFONE
import br.fernando.agendaprospera.model.Constantes.Companion.TABELA_CLIENTE
import br.fernando.agendaprospera.model.dominio.Cliente

class ClienteDAO(context: Context) {

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

    fun salvarCliente(cliente: Cliente): Long {
//        ABRINDO BANCO DE DADOS
        val db = abrirBanco()
//        ENVIANDO DADOS PARA O BANCO
        val values = ContentValues().apply {
            put(COLUNA_CLIENTE_NOME, cliente.nome)
            put(COLUNA_CLIENTE_APELIDO, cliente.apelido)
            put(COLUNA_CLIENTE_RUA, cliente.rua)
            put(COLUNA_CLIENTE_NUMERO, cliente.numero)
            put(COLUNA_CLIENTE_COMPLEMENTO, cliente.complemento)
            put(COLUNA_CLIENTE_BAIRRO, cliente.bairro)
            put(COLUNA_CLIENTE_CIDADE, cliente.cidade)
            put(COLUNA_CLIENTE_TELEFONE, cliente.telefone)
            put(COLUNA_CLIENTE_LONGITUDE, cliente.longitude)
            put(COLUNA_CLIENTE_LATITUDE, cliente.latitude)
            put(COLUNA_CLIENTE_ATIVIDADE, cliente.atividade)
            put(COLUNA_CLIENTE_HORARIO, cliente.horario)
            put(COLUNA_CLIENTE_DATA, cliente.data)
            put(COLUNA_CHAVE_ESTRANGEIRA_AGENTE, cliente.agenteId)
        }
//        faendo O INSERT NO BANCO DE DADOS
        return db.insert(TABELA_CLIENTE, null, values)
    }

    fun editarCliente(cliente: Cliente): Long {
//        abrir banco de dados
        val db = abrirBanco()
//        preparando os dados par atualiacao no banco de dados
        val values = ContentValues().apply {
            put(COLUNA_CLIENTE_NOME, cliente.nome)
            put(COLUNA_CLIENTE_APELIDO, cliente.apelido)
            put(COLUNA_CLIENTE_RUA, cliente.rua)
            put(COLUNA_CLIENTE_NUMERO, cliente.numero)
            put(COLUNA_CLIENTE_COMPLEMENTO, cliente.complemento)
            put(COLUNA_CLIENTE_BAIRRO, cliente.bairro)
            put(COLUNA_CLIENTE_CIDADE, cliente.cidade)
            put(COLUNA_CLIENTE_TELEFONE, cliente.telefone)
            put(COLUNA_CLIENTE_LONGITUDE, cliente.longitude)
            put(COLUNA_CLIENTE_LATITUDE, cliente.latitude)
            put(COLUNA_CLIENTE_ATIVIDADE, cliente.atividade)
            put(COLUNA_CLIENTE_HORARIO, cliente.horario)
            put(COLUNA_CLIENTE_DATA, cliente.data)
            put(COLUNA_CHAVE_ESTRANGEIRA_AGENTE, cliente.agenteId)
        }
//        argumento para comparar identificador
        val selecao = COLUNA_CLIENTE_ID + " = ?"
//        argumento para que recebe o identificador do obejto
        val selecaoArgumento = arrayOf(cliente.id.toString())
//        faendo a atualiacao dos dados no banco de dados segundo os argumentos
        return db.update(TABELA_CLIENTE, values, selecao, selecaoArgumento).toLong()

    }

    fun buscarCliente(id: Int): Cliente {
        //        GERANDO OBJETO cliente
        val cliente = Cliente()
//        lendo O BANCO DE DADOS
        val db = lerBanco()
//        SCRIPT PARA REALIAR A CONSULTA NO BANCO DE DADOS
        val selectQuery =
            "SELECT * FROM $TABELA_CLIENTE WHERE $COLUNA_CLIENTE_ID = ? $id"
//        CURSOR PARA BUSCAR O OBJETO NO BANCO DE DADOS
        val cursor = db.rawQuery(selectQuery, null)
        cursor?.moveToFirst()
        cliente.id = cursor.getInt(cursor.getColumnIndex(COLUNA_CLIENTE_ID))
        cliente.nome = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_NOME))
        cliente.apelido = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_APELIDO))
        cliente.rua = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_RUA))
        cliente.numero = cursor.getInt(cursor.getColumnIndex(COLUNA_CLIENTE_NUMERO))
        cliente.complemento = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_COMPLEMENTO))
        cliente.bairro = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_BAIRRO))
        cliente.cidade = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_CIDADE))
        cliente.telefone = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_TELEFONE))
        cliente.longitude = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_LONGITUDE))
        cliente.latitude = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_LATITUDE))
        cliente.atividade = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_ATIVIDADE))
        cliente.horario = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_HORARIO))
        cliente.data = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_DATA))
        cliente.agenteId = cursor.getInt(cursor.getColumnIndex(COLUNA_CHAVE_ESTRANGEIRA_AGENTE))
        cursor.close()
        return cliente
    }

    fun listarCliente(): ArrayList<Cliente> {
//        criando colecao do tipo do objeto
        val clienteLista = ArrayList<Cliente>()
//        lendo o banco de dados
        val db = lerBanco()
//        argumento para consulta
        val selecaoArgumento = "SELECT * FROM $TABELA_CLIENTE"
//        percorrendo banco de dados com o cursor
        val cursor = db.rawQuery(selecaoArgumento, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val cliente = Cliente()
//                    coletando dados
                    cliente.id = cursor.getInt(cursor.getColumnIndex(COLUNA_CLIENTE_ID))
                    cliente.nome = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_NOME))
                    cliente.apelido = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_APELIDO))
                    cliente.rua = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_RUA))
                    cliente.numero = cursor.getInt(cursor.getColumnIndex(COLUNA_CLIENTE_NUMERO))
                    cliente.complemento = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_COMPLEMENTO))
                    cliente.bairro = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_BAIRRO))
                    cliente.cidade = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_CIDADE))
                    cliente.telefone = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_TELEFONE))
                    cliente.longitude = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_LONGITUDE))
                    cliente.latitude = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_LATITUDE))
                    cliente.atividade = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_ATIVIDADE))
                    cliente.horario = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_HORARIO))
                    cliente.data = cursor.getString(cursor.getColumnIndex(COLUNA_CLIENTE_DATA))
                    cliente.agenteId = cursor.getInt(cursor.getColumnIndex(COLUNA_CHAVE_ESTRANGEIRA_AGENTE))
//                    adicionando dados na colecao
                    clienteLista.add(cliente)
                } while (cursor.moveToNext())
            }
        }
//        fecha cursor
        cursor.close()
//        retorna a lista de dados
        return clienteLista
    }

    fun excluirCliente(id: Int): Boolean {
//        abrindo o abrindo o banco de dados
        val db = abrirBanco()
//        argumento para comparacao de identificador
        val selecaoClausula = "$COLUNA_CLIENTE_ID = ? $id"
//        argumento passado por parametro
        val argumento = arrayOf(id.toString())
//        removendo objeto do banco de dados
        val removido: Int = db.delete(TABELA_CLIENTE, selecaoClausula, argumento)
        return removido > 0
    }


}