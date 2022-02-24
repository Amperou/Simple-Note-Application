package com.example.noteapplication

import androidx.lifecycle.LiveData
import androidx.room.*

//annotation pour la class Dao
@Dao
interface NoteDao {
    //Methode pour l'insertion d'un nouvel element dans la DB
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    //Methode permettant de mettre à jour une note
    @Update
    suspend fun update(note: Note)

    //Methode pour la suppression d'une Note
    @Delete
    suspend fun delete(note: Note)
    //Definition d'une requet
    // retournant la liste de toute les notes
    //ordonnée dans le sens croissant suivant l'ID
    @Query("Select * from noteTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

}