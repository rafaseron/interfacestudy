package br.com.rafaseron.interfacestudy.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import br.com.rafaseron.interfacestudy.domain.Carro

class StoredCarDatabaseManager {

    fun insertCar (contexto: Context, carro: Carro){    //funcao para inserir um carro no banco de dados

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


    fun searchId(contexto: Context, id: Int) : Carro{   //funcao para buscar um carro no banco de dados - ou ver se o carro existe/ja foi inserido
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
            columnData, //colunas que queremos
            filterColumnData, //colunas que queremos filtrar
            idToString, //valores que queremos filtrar
            null,
            null,
            null,)

        var objectCar = Carro(0, "", "", "", "", "")
        with(cursor){
            while (moveToNext()){
                val itemId = getInt(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_ID))
                Log.e("searchid ->", id.toString())
                val preco = getString(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_PRECO))
                Log.e("searchid ->", preco)
                val bateria = getString(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_BATERIA))
                Log.e("searchid ->", bateria)
                val potencia = getString(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_POTENCIA))
                Log.e("searchid ->", potencia)
                val recarga = getString(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_RECARGA))
                Log.e("searchid ->", recarga)
                val urlPhoto = getString(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_URL_PHOTO))
                Log.e("searchid ->", urlPhoto)

                objectCar = Carro(itemId, preco, bateria, potencia, recarga, urlPhoto)
            }
        }
        cursor.close()
        return objectCar
    }

    fun insertOnlyIfNotExists(contexto: Context, carro: Carro){
        val carroExistente = searchId(contexto, carro.id)
        if (carroExistente.id == 0){
            insertCar(contexto, carro)
        }
    }

    fun getAllSavedData(contexto: Context): ArrayList<Carro>{
        val dbHelper = StoredCarDbHelper(contexto)
        val db = dbHelper.readableDatabase

        val columnData = arrayOf(BaseColumns._ID,
            StoredCarInterface.StoredCarData.COLUMN_NAME_ID,
            StoredCarInterface.StoredCarData.COLUMN_NAME_PRECO,
            StoredCarInterface.StoredCarData.COLUMN_NAME_BATERIA,
            StoredCarInterface.StoredCarData.COLUMN_NAME_POTENCIA,
            StoredCarInterface.StoredCarData.COLUMN_NAME_RECARGA,
            StoredCarInterface.StoredCarData.COLUMN_NAME_URL_PHOTO)

        val cursor = db.query(
            StoredCarInterface.StoredCarData.TABLE_NAME,
            columnData,
            null,
            null,
            null,
            null,
            null)

        val carrosArray = ArrayList<Carro>()
        with(cursor){
            while (moveToNext()){
                val itemId = getInt(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_ID))
                val preco = getString(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_PRECO))
                val bateria = getString(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_BATERIA))
                val potencia = getString(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_POTENCIA))
                val recarga = getString(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_RECARGA))
                val urlPhoto = getString(getColumnIndexOrThrow(StoredCarInterface.StoredCarData.COLUMN_NAME_URL_PHOTO))

                val moldeCarro = Carro(itemId, preco, bateria, potencia, recarga, urlPhoto)

                carrosArray.add(moldeCarro)
            }
        }
        cursor.close()
        return carrosArray
    }

    fun deleteCar(contexto: Context, id: Int){
        val dbHelper = StoredCarDbHelper(contexto)
        val db = dbHelper.writableDatabase
        val filterColumnData = "${StoredCarInterface.StoredCarData.COLUMN_NAME_ID} = ?"
        val idToString = arrayOf(id.toString())
        db.delete(StoredCarInterface.StoredCarData.TABLE_NAME, filterColumnData, idToString)
        Log.e("deleteCar", "ID ${id.toString()} deletado com sucesso")
    }

    fun isStored(contexto: Context, id: Int): Boolean{
        val carro = searchId(contexto, id)
        if (carro.id == 0){
            return false
        }else{
            return true
        }
    }

}