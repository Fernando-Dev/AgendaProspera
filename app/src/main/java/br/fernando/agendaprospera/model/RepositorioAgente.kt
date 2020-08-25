package br.fernando.agendaprospera.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_AGENTE_ID
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_AGENTE_LOGIN
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_AGENTE_NOME
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_AGENTE_SENHA
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CHAVE_ESTRANGEIRA_AGENTE
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CHAVE_ESTRANGEIRA_CLIENTE
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE
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
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_QUILOMETRAGEM_DISTANCIA
import br.fernando.agendaprospera.model.Constantes.Companion.COLUNA_QUILOMETRAGEM_ID
import br.fernando.agendaprospera.model.Constantes.Companion.DB_NOME
import br.fernando.agendaprospera.model.Constantes.Companion.DB_VERSAO
import br.fernando.agendaprospera.model.Constantes.Companion.TABELA_AGENTE
import br.fernando.agendaprospera.model.Constantes.Companion.TABELA_CLIENTE
import br.fernando.agendaprospera.model.Constantes.Companion.TABELA_QUILOMETRAGEM


class RepositorioAgente(context: Context) :
    SQLiteOpenHelper(context, DB_NOME, null, DB_VERSAO) {

    override fun onCreate(db: SQLiteDatabase?) {
        /**
         * CRIANDO A TABELA AGENTE
         */
        val CREATE_TABLE_AGENTE = "CREATE TABLE " +
                "$TABELA_AGENTE($COLUNA_AGENTE_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "$COLUNA_AGENTE_NOME TEXT NOT NUL," +
                "$COLUNA_AGENTE_LOGIN TEXT NOT NULL," +
                "$COLUNA_AGENTE_SENHA TEXT NOT NULL);"
        /**
         * EXECUTA SCRIPT
         */
        db?.execSQL(CREATE_TABLE_AGENTE)

        /**
         * CRIANDO A TABELA CLIENTE
         */

        val CREATE_TABLE_CLIENTE = "CREATE TABLE " +
                "$TABELA_CLIENTE($COLUNA_CLIENTE_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "$COLUNA_CLIENTE_NOME TEXT NOT NULL," +
                "$COLUNA_CLIENTE_APELIDO TEXT NOT NULL," +
                "$COLUNA_CLIENTE_RUA TEXT NOT NULL," +
                "$COLUNA_CLIENTE_NUMERO INTEGER NOT NULL," +
                "$COLUNA_CLIENTE_COMPLEMENTO TEXT," +
                "$COLUNA_CLIENTE_BAIRRO TEXT NOT NULL," +
                "$COLUNA_CLIENTE_CIDADE TEXT NOT NULL," +
                "$COLUNA_CLIENTE_TELEFONE TEXT NOT NULL," +
                "$COLUNA_CLIENTE_LONGITUDE TEXT NOT NULL," +
                "$COLUNA_CLIENTE_LATITUDE TEXT NOT NULL," +
                "$COLUNA_CLIENTE_ATIVIDADE TEXT NOT NULL," +
                "$COLUNA_CLIENTE_HORARIO TEXT NOT NULL," +
                "$COLUNA_CLIENTE_DATA TEXT NOT NULL," +
                "$COLUNA_CHAVE_ESTRANGEIRA_AGENTE INTEGER NOT NULL);"
        /**
         *EXECUTA SCRIPT
         */
        db?.execSQL(CREATE_TABLE_CLIENTE)

        /**
         * CRIANDO A TABELA QUILOMETRAGEM
         */

        val CREATE_TABLE_QUILOMETRAGEM = "CREATE TABLE" +
                "$TABELA_QUILOMETRAGEM($COLUNA_QUILOMETRAGEM_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "$COLUNA_QUILOMETRAGEM_DISTANCIA INTEGER NOT NULL," +
                "$COLUNA_CHAVE_ESTRANGEIRA_CLIENTE INTEGER NOT NULL," +
                "$COLUNA_CHAVE_ESTRANGEIRA_CLIENTE_AGENTE INTEGER NOT NULL);"
        /**
         * executa script
         */
        db?.execSQL(CREATE_TABLE_QUILOMETRAGEM)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        /**
         * APAGA TODOS OS DADOS DA TABELA CASO SEJA MODIFICADA
         */
        val DROP_TABLE_AGENTE = "DROP TABLE IF EXISTS $TABELA_AGENTE"
        val DROP_TABLE_CLIENTE = "DROP TABLE IF EXISTS $TABELA_CLIENTE"
        val DROP_TABLE_QUILOMETRAGEM = "DROP TABLE IF EXISTS $TABELA_QUILOMETRAGEM"

        /**
         * EXECUTA SCRIPT
         */

        db?.execSQL(DROP_TABLE_AGENTE)
        db?.execSQL(DROP_TABLE_CLIENTE)
        db?.execSQL(DROP_TABLE_QUILOMETRAGEM)

        /**
         * executa metodo oncreate para criar um banco novo
         */
        onCreate(db)
    }

}