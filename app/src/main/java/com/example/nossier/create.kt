package com.example.nossier

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment

class create : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val Today = view.findViewById<AppCompatButton>(R.id.Today)
        val Yesterday = view.findViewById<AppCompatButton>(R.id.YesterDay)
        val OtherDay = view.findViewById<AppCompatButton>(R.id.OtherDay)

        Today.setOnClickListener {
            val intent = Intent(requireActivity(), EnteringData::class.java)
            startActivity(intent)
            requireFragmentManager().beginTransaction().remove(this).commit()
        }
        Yesterday.setOnClickListener {
            val intent = Intent(requireActivity(), EnteringData::class.java)
            startActivity(intent)
            requireFragmentManager().beginTransaction().remove(this).commit()
        }
        OtherDay.setOnClickListener {
            val intent = Intent(requireActivity(), EnteringData::class.java)
            startActivity(intent)
            requireFragmentManager().beginTransaction().remove(this).commit()
        }
    }

}