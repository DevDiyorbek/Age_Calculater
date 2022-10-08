package com.example.aimcalculater

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selectedDate : TextView? = null
    private var minutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myButton : Button = findViewById(R.id.selectDate)
        selectedDate = findViewById(R.id.textView4)
        minutes = findViewById(R.id.textView6)

        myButton.setOnClickListener {
            clickDataPicker()

        }
    }

    private fun clickDataPicker (){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val aim = DatePickerDialog(this,
            { _, birthYear, birthMonth, birthDayOfMonth ->

                Toast.makeText(this,
                    "Motivatsiya: Do'g'ilgoningo shuncha voht bo'ldi, bir ishni gullatmadingay",
                    Toast.LENGTH_LONG).show()

                val newDate = "$birthDayOfMonth/${birthMonth+1}/$birthYear"
                selectedDate?.text = newDate

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val theDate = sdf.parse(newDate)
                theDate?.let {
                    val selectedTimeInMinutes = theDate.time/60000

                    val currendDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currendDate?.let {
                        val currentDateInMinutes = currendDate.time/60000

                        val difference = currentDateInMinutes - selectedTimeInMinutes

                        minutes?.text = difference.toString()
                    } }

            },year,month,day)

        aim.datePicker.maxDate = System.currentTimeMillis() - 86400000

        aim.show()
    }



}