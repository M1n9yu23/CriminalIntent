package com.bignerdranch.android.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

private const val ARG_DATE = "date"
const val REQUEST_KEY_DATE = "request_key_date"
const val RESULT_DATE_KEY = "result_date_key"

class DatePickerFragment: DialogFragment() {

    interface Callbacks {
        fun onDateSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dateListener = DatePickerDialog.OnDateSetListener {
                _: DatePicker, year: Int, month: Int, day: Int ->
            val resultDate: Date = GregorianCalendar(year, month, day).time

            @Suppress("DEPRECATION")
            targetFragment?.let { fragment ->
                (fragment as Callbacks).onDateSelected(resultDate)
            }
        }
        @Suppress("DEPRECATION")
        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calender = Calendar.getInstance()
        calender.time = date // date 객체를 calendar 객체로 참조
        val initialYear = calender.get(Calendar.YEAR)
        val initialMonth = calender.get(Calendar.MONTH)
        val initialDay = calender.get(Calendar.MONTH)

        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )
    }

    companion object {
        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }

            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }
}