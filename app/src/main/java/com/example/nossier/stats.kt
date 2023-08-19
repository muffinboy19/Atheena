package com.example.nossier

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import org.w3c.dom.Text


class stats : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val happyCountTextView = view.findViewById<TextView>(R.id.happyCountTextView)
        val sadCountTextView = view.findViewById<TextView>(R.id.sadCountTextView)
        val joyfulCountTextView = view.findViewById<TextView>(R.id.joyfulCountTextView)
        val ExtremeSadTextView = view.findViewById<TextView>(R.id.ExtremeSadTextView)
        val NormalTextView = view.findViewById<TextView>(R.id.NormalTextView)

        // Retrieve mood counts and observe changes
        val happyCount = MoodCountRepository.getMoodCount("Happy")
        happyCount.observe(viewLifecycleOwner, Observer { count ->
            happyCountTextView.text = "Happy: $count"
        })

        val sadCount = MoodCountRepository.getMoodCount("Sad")
        sadCount.observe(viewLifecycleOwner, Observer { count ->
            sadCountTextView.text = "Sad: $count"
        })

        val joyfulCount = MoodCountRepository.getMoodCount("Joyful")
        joyfulCount.observe(viewLifecycleOwner, Observer { count ->
            joyfulCountTextView.text = "Joyful: $count"
        })
        val ExtremeSadCount = MoodCountRepository.getMoodCount("Joyful")
        joyfulCount.observe(viewLifecycleOwner, Observer { count ->
            ExtremeSadTextView.text = "Joyful: $count"
        })
        val NormalCount = MoodCountRepository.getMoodCount("Joyful")
        joyfulCount.observe(viewLifecycleOwner, Observer { count ->
            NormalTextView.text = "Joyful: $count"
        })

        // Observe other mood counts as needed
    }

}