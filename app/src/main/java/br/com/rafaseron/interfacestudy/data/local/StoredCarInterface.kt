package br.com.rafaseron.interfacestudy.data.local

import android.provider.BaseColumns

object StoredCarInterface {

    const val DATABASE_NAME = "dbCar.db"
    const val DATABASE_VERSION = 1

    object StoredCarData: BaseColumns{
        const val TABLE_NAME = "car"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_PRECO = "preco"
        const val COLUMN_NAME_BATERIA = "bateria"
        const val COLUMN_NAME_POTENCIA = "potencia"
        const val COLUMN_NAME_RECARGA = "recarga"
        const val COLUMN_NAME_URL_PHOTO = "url_photo"
    }

    const val SQL_CREATE_STOREDCARTABLE_QUERY = "CREATE_TABLE ${StoredCarData.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${StoredCarData.COLUMN_NAME_ID} TEXT," +
            "${StoredCarData.COLUMN_NAME_PRECO} TEXT," +
            "${StoredCarData.COLUMN_NAME_BATERIA} TEXT," +
            "${StoredCarData.COLUMN_NAME_POTENCIA} TEXT," +
            "${StoredCarData.COLUMN_NAME_RECARGA} TEXT," +
            "${StoredCarData.COLUMN_NAME_URL_PHOTO} TEXT)"

    const val SQL_DELETE_STOREDCARTABLE_QUERY = "DROP TABLE IF EXISTS ${StoredCarData.TABLE_NAME}"

}