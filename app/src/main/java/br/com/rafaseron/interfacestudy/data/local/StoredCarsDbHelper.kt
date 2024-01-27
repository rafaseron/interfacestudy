package br.com.rafaseron.interfacestudy.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.rafaseron.interfacestudy.data.local.StoredCarsInterface

class StoredCarsDbHelper (context: Context): SQLiteOpenHelper(context, StoredCarsInterface.DATABASE_NAME, null, StoredCarsInterface.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) { //db é o p0
        db?.let{
            it.execSQL(StoredCarsInterface.SQL_CREATE_STORED_CAR_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { //oldVersion é o p1 e newVersion é o p2
        db?.let{
            it.execSQL(StoredCarsInterface.SQL_DELETE_STORED_CAR_TABLE)
            onCreate(db)
        }
    }


}