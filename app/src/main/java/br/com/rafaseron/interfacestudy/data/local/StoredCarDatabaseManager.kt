package br.com.rafaseron.interfacestudy.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import br.com.rafaseron.interfacestudy.domain.Carro

class StoredCarDatabaseManager {

    fun insertCar (contexto: Context, carro: Carro){

        try {
            val dbHelper = StoredCarDbHelper(contexto)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(StoredCarInterface.StoredCarData.COLUMN_NAME_ID, carro.id)
                put(StoredCarInterface.StoredCarData.COLUMN_NAME_PRECO, carro.preco)
                put(StoredCarInterface.StoredCarData.COLUMN_NAME_BATERIA, carro.bateria)
                put(StoredCarInterface.StoredCarData.COLUMN_NAME_POTENCIA, carro.potencia)
                put(StoredCarInterface.StoredCarData.COLUMN_NAME_RECARGA, carro.recarga)
                put(StoredCarInterface.StoredCarData.COLUMN_NAME_URL_PHOTO, carro.urlPhoto)
            }
            db.insert(StoredCarInterface.StoredCarData.TABLE_NAME, null, values)

        }catch (excecao: Exception){
            excecao.message?.let {
                Log.e("DB Insert Error", it)
            }
        }

    }


    fun searchId(contexto: Context, id: Int){
        val dbHelper = StoredCarDbHelper(contexto)
        val db = dbHelper.readableDatabase

        //armazenando os dados das Colunas em um Array
        val columnData = arrayOf(BaseColumns._ID,
            StoredCarInterface.StoredCarData.COLUMN_NAME_ID,
            StoredCarInterface.StoredCarData.COLUMN_NAME_PRECO,
            StoredCarInterface.StoredCarData.COLUMN_NAME_BATERIA,
            StoredCarInterface.StoredCarData.COLUMN_NAME_POTENCIA,
            StoredCarInterface.StoredCarData.COLUMN_NAME_RECARGA,
            StoredCarInterface.StoredCarData.COLUMN_NAME_URL_PHOTO)

        val filterColumnData = "${StoredCarInterface.StoredCarData.COLUMN_NAME_ID} = ?"
        val idToString = arrayOf(id.toString())

        val cursor = db.query(
            StoredCarInterface.StoredCarData.TABLE_NAME, //nome da tabela
            columnData,
            filterColumnData,
            idToString,
            null,
            null,
            null,)

        val itemCar = mutableListOf<Carro>()
        with(cursor){
            while (moveToNext()){
                val itemId = getInt(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_ID))
                Log.e("searchid ->", itemId.toString())
            }
        }
        cursor.close()

    }

}