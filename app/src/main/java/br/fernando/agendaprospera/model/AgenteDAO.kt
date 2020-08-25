package br.fernando.agendaprospera.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase

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



}