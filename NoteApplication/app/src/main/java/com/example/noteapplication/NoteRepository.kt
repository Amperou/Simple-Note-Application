package com.example.noteapplication

import androidx.lifecycle.LiveData

class NoteRepository (private  val notesDao: NoteDao){

    //Creation d'une variable liste
    //Recuperation de toute les notes depuis la class Dao
    val allNote: LiveData<List<Note>> = notesDao.getAllNotes()
    //Methode permettant l'insertion d'une note dans la DB
    suspend fun insert(note: Note){
        notesDao.insert(note)
    }
    //Methode permettant la suppression d'une note dans la DB
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }
    //Methode de mise à jour d'une note dans la DB
    suspend fun update(note : Note){
        notesDao.update(note)
    }


}