package com.bignerdranch.android.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DatePickerFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calender = Calendar.getInstance()
        val initialYear = calender.get(Calendar.YEAR)
        val initialMonth = calender.get(Calendar.MONTH)
        val initialDay = calender.get(Calendar.MONTH)

        return DatePickerDialog(
            requireContext(),
            null,
            initialYear,
            initialMonth,
            initialDay
        )
    }
}