package br.com.rafaseron.interfacestudy.data.local

import android.content.ContentValues
import android.content.Context
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

}