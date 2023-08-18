package com.example.nossier

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class entreis : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var database: DatabaseReference
    private val moodCountMap = HashMap<String, Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_entreis, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseApp.initializeApp(requireContext())
        mAuth = FirebaseAuth.getInstance() // Initialize mAuth
        database = FirebaseDatabase.getInstance().reference // Initialize database

        notesRecyclerView = view.findViewById(R.id.notesRecyclerView)
        notesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val userId = mAuth.currentUser?.uid
        if (userId != null) {
            val userNotesRef = database.child("notes").child("users").child(userId).child("notes")
            userNotesRef.addValueEventListener(object : ValueEventListener {


                override fun onDataChange(snapshot: DataSnapshot) {
                    moodCountMap.clear()

                    Toast.makeText(requireContext(),"busssa",Toast.LENGTH_SHORT).show()
                    val notesList = mutableListOf<Note>()
                        for (noteSnapshot in snapshot.children) {
                            val note = noteSnapshot.getValue(Note::class.java)
                            note?.let {
                                notesList.add(note)
                                MoodCountRepository.incrementMoodCount(note.mood)// Assuming there's a 'mood' property in your Note class

                            }
                        }

                    Log.d("NotesDebug", "Number of notes retrieved: ${notesList.size}")
                    val notesAdapter = NotesAdapter(notesList)
                    notesRecyclerView.adapter = notesAdapter
                    notesAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(),"error in the notes bro",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


}
