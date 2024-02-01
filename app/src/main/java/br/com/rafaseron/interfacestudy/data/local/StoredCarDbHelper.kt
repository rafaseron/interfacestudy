package br.com.rafaseron.interfacestudy.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.rafaseron.interfacestudy.domain.Carro

class StoredCarDbHelper (context: Context): SQLiteOpenHelper(context, StoredCarInterface.DATABASE_NAME, null, StoredCarInterface.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) { //db é o p0
        db?.let{    //consequencia da modulariazao de codigo: precisamos nos preocupar na possibilidade dos dados serem nulos
            it.execSQL(StoredCarInterface.SQL_CREATE_STOREDCARTABLE_QUERY)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { //oldVersion é o p1 e newVersion é o p2
        db?.let{
            it.execSQL(StoredCarInterface.SQL_DELETE_STOREDCARTABLE_QUERY) //antes da alteracao (upgrade) é deletado
            onCreate(db)
        }
        //basicamente no upgrade temos o processo de DELETAR e depois CRIAR novamente, para assim também, alterar da oldVersion para a newVersion
        //também há como implementar uma logica de migracao de dados mais complexa, a fim de PRESERVAR os dados existentes durante um upgrade
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.let{
            it.execSQL(StoredCarInterface.SQL_DELETE_STOREDCARTABLE_QUERY) //deleta a tabela existente
            onCreate(db) //cria a tabela novamente
        }
    }

    /*
    fun insertCar (carro: Carro){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(StoredCarInterface.StoredCarData.COLUMN_NAME_ID, carro.id)
            put(StoredCarInterface.StoredCarData.COLUMN_NAME_PRECO, carro.preco)
            put(StoredCarInterface.StoredCarData.COLUMN_NAME_BATERIA, carro.bateria)
            put(StoredCarInterface.StoredCarData.COLUMN_NAME_POTENCIA, carro.potencia)
            put(StoredCarInterface.StoredCarData.COLUMN_NAME_RECARGA, carro.recarga)
            put(StoredCarInterface.StoredCarData.COLUMN_NAME_URL_PHOTO, carro.urlPhoto)
        }
        // val newRegister = db.insert(StoredCarsInterface.StoredCarData.TABLE_NAME, null, values)
        db.insert(StoredCarInterface.StoredCarData.TABLE_NAME, null, values)
    }
    */


}