package com.example.noteapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract  class NoteDatabase :RoomDatabase() {
    abstract  fun getNotesDao(): NoteDao
    companion object{

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context):NoteDatabase{
            //Retourne l'intance de DB si elle n'est pas vide
            //Sinon créé une la DB
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                //Retournez l'instance
                instance
            }
        }
    }
}