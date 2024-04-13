package com.example.rickandmorty.data.localDataSource.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.util.Constants.DATA_BASE_NAME

class RickAndMortyLocalDataBase(context: Context): SQLiteOpenHelper(context, DATA_BASE_NAME,null, 1 ), RickAndMortyLocalDataBaseService{
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE personajes" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT , species TEXT, image TEXT , " +
                "status TEXT, type, TEXT, location TEXT, origin TEXT, " +
                "gender TEXT, page TEXT)"
         db?.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS $DATA_BASE_NAME"
        db?.execSQL(ordenBorrado)
        onCreate(db)
    }

    override suspend fun setAllCharactersByPage(page: String, characters: List<Character>) {
        val dataInsert = ContentValues()
        val db = this.writableDatabase
        for (i in 0 until characters.size) {
            val character = characters[i]
            dataInsert.put("name", character.name)
            dataInsert.put("species", character.species)
            dataInsert.put("image", character.image)
            dataInsert.put("status", character.status)
            dataInsert.put("type", character.type)
            dataInsert.put("location", character.location)
            dataInsert.put("origin", character.origin)
            dataInsert.put("gender", character.gender)
            dataInsert.put("page", page)
            db.insert("personajes", null, dataInsert)
        }
    }

    override suspend fun getAllCharactersByPage(page: String): List<Character> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM personajes WHERE page =?", arrayOf(page))
        val characters = mutableListOf<Character>()
        if (cursor != null ){
            if (cursor.moveToFirst()) {
                do {
                    val character = Character(
                        name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        species = cursor.getString(cursor.getColumnIndexOrThrow("species")),
                        gender = cursor.getString(cursor.getColumnIndexOrThrow("gender")),
                        image = cursor.getString(cursor.getColumnIndexOrThrow("image")),
                        status = cursor.getString(cursor.getColumnIndexOrThrow("status")),
                        type = cursor.getString(cursor.getColumnIndexOrThrow("type")),
                        location = cursor.getString(cursor.getColumnIndexOrThrow("location")),
                        origin = cursor.getString(cursor.getColumnIndexOrThrow("origin")),
                    )
                    characters.add(character)

                } while (cursor.moveToNext())
            }
        }else return emptyList()

        cursor.close()
        return characters
    }


}