package com.example.nossier

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import java.util.Date

class create : DialogFragment() {
    private var shouldDismiss = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val Today = view.findViewById<ImageView>(R.id.Today)
        val Yesterday = view.findViewById<ImageView>(R.id.YesterDay)
        val OtherDay = view.findViewById<ImageView>(R.id.OtherDay)
        val pussa = view.findViewById<View>(R.id.mussa)

        Today.setOnClickListener {
            val currentDate = Calendar.getInstance().time
            startEnteringDataActivity(currentDate)
            requireFragmentManager().beginTransaction().remove(this).commit()
            // shouldDismiss = true
        }
        Yesterday.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            val yesterdayDate = calendar.time
            startEnteringDataActivity(yesterdayDate)
            requireFragmentManager().beginTransaction().remove(this).commit()
            // shouldDismiss = true
        }
        OtherDay.setOnClickListener {
            openCalendar()
            requireFragmentManager().beginTransaction().remove(this).commit()
        }
        pussa.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .remove(this@create)
                .commit()
        }


    }

     private fun openCalendar() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

         val datePickerDialog = DatePickerDialog(
             requireContext(),
             DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                 val calendar = Calendar.getInstance()
                 calendar.set(selectedYear, selectedMonth, selectedDay)
                 val selectedDateInMillis = calendar.timeInMillis

                 val intent = Intent(requireContext(), EnteringData::class.java)
                 intent.putExtra("selectedDate", selectedDateInMillis)
                 startActivity(intent)
             },
             year, month, day
        )

        datePickerDialog.show()
    }
    private fun startEnteringDataActivity(selectedDate: Date) {
        val intent = Intent(requireActivity(), EnteringData::class.java)
        intent.putExtra("selectedDate", selectedDate.time)
        startActivity(intent)
    }
}