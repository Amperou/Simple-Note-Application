package com.example.androidroommvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")

class Word(
    @ColumnInfo(name = "word") val word: String
){

    @PrimaryKey(autoGenerate = true) var id = 0
}