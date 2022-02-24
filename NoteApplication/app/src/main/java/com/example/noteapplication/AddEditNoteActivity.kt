package com.example.noteapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    // Creating variables for our UI components.
    lateinit var  noteTitleEdt : EditText
    lateinit var noteDescriptionEdt: EditText
    lateinit var  updateBtn: Button
    //creating variable for view model and and integer for our note id.
    lateinit var viewModel : NoteViewModel
    var noteID = -1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        noteTitleEdt = findViewById(R.id.idEditNoteTitle)
        noteDescriptionEdt = findViewById(R.id.idEditNoteDescription)
        updateBtn = findViewById(R.id.idBtnAddUpdate)
        //initialisation of our view modal
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        val noteType = intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            //setting data to edit text
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID", -1 )
            updateBtn.text = "Update Note"
            noteTitleEdt.setText(noteTitle)
            noteDescriptionEdt.setText(noteDesc)
        }else{
            updateBtn.text = "save Note"
        }
        //adding click listener to our save button.
        updateBtn.setOnClickListener {
            // on below line we are getting
            // title and desc from edit text.
            val noteTitle = noteTitleEdt.text.toString()
            val noteDescription = noteDescriptionEdt.text.toString()
            // on below line we are checking the type
            // and then saving or updating the data.
            if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                val sdf = SimpleDateFormat("dd MMM, yyy - HH:mm")
                val currentDate: String = sdf.format(Date())
                val updateNote = Note(noteTitle, noteDescription, currentDate)
                updateNote.id = noteID
                viewModel.updateNote(updateNote)
                Toast.makeText(this, "Note Update ...", Toast.LENGTH_LONG).show()
            }else{
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty() ){
                    val sdf = SimpleDateFormat("dd MMM, yyy  - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    // if the string is not empty we are calling a
                    // add note method to add data to our room database.
                    viewModel.addNote(Note(noteTitle, noteDescription, currentDate))
                    Toast.makeText(this, "$noteTitle Added...", Toast.LENGTH_LONG).show()

                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}