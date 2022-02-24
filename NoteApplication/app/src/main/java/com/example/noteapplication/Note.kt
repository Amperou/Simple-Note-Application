package com.example.noteapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Specification du nom de la table
@Entity(tableName = "noteTable")
class Note(
    //Specification des iformation des colonnes
    // avec  passage des nom de colonne
    @ColumnInfo(name="title") val noteTitle:String,
    @ColumnInfo(name="description") val noteDescription:String,
    @ColumnInfo(name="timestamp") val timestamp:String
    ) {
    //Definition d'une clée primaire
    // qui s'autoincremente avec une valeur initiale à 0
    @PrimaryKey(autoGenerate = true) var id = 0
}