package com.example.noteapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(
    val context: Context,
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface
    ):RecyclerView.Adapter<NoteRVAdapter.ViewHolder>(){
    private val allNotes = ArrayList<Note>() //variable cotenant la liste des notes
    //creation de la class view holder
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //definition et initialisation des variables s'affichant dans la vue
        val noteTV: TextView = itemView.findViewById<TextView>(R.id.idTVNoteTitle)
        val timeTV: TextView = itemView.findViewById<TextView>(R.id.idTVTimeStamp)
        val deleteIV: TextView = itemView.findViewById<TextView>(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //mise en page pour chaque élément du recycle view .
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //setting data to item of recycler view
        holder.noteTV.text = allNotes[position].noteTitle
        holder.timeTV.text = "Last Updated: "+ allNotes[position].timestamp
        //adding click listener to our delete image view icon.
        holder.deleteIV.setOnClickListener{
            //calling a note click interface
            // passing a position to it.
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }
        //Ajout d'un click listner
        holder.itemView.setOnClickListener{
            //appel de l'interface note de click
            //passage de la position
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    override fun getItemCount(): Int {
        //retourne la taille de la liste de notes
       return  allNotes.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>){
        //Effaçage de la liste des note
        allNotes.clear()
        //Ajout d'une nouvelle liste de note
        allNotes.addAll(newList)
        //Notification des changement opéré à l'adaptateur
        notifyDataSetChanged()
    }
}

interface  NoteClickDeleteInterface{
    //Action sur le click de l'icone de suppression de note
    fun onDeleteIconClick(note: Note)
}

interface  NoteClickInterface{
    //Action sur le click d'une note
    fun onNoteClick(note: Note)
}
