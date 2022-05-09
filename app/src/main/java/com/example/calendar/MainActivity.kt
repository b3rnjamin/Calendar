package com.example.calendar

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity(), OnCalendarClickListener {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var selectedDate: LocalDate
    private val adapterCalendar = AdapterCalendar(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        selectedDate = LocalDate.now()

        binding.rvMonth.apply {
            layoutManager = SpanningGridLayoutManager(context, 7, RecyclerView.VERTICAL, false)
            adapter = adapterCalendar
        }

        setMonth()

        binding.imageView2.setOnClickListener {
            selectedDate = selectedDate.minusMonths(1)
            setMonth()
        }

        binding.imageView3.setOnClickListener {
            selectedDate = selectedDate.plusMonths(1)
            setMonth()
        }
    }

    private fun setMonth(){
        binding.tvTitleMonth.text = monthYearFromDate(selectedDate)
        val dates = daysInMonthArray(selectedDate)
        adapterCalendar.setData(dates)
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<Data> {
        val daysInMonthArray: ArrayList<Data> = arrayListOf()
        val yearMonth: YearMonth = YearMonth.from(date)
        val daysInMonth: Int = yearMonth.lengthOfMonth()

        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add(Data(""))
            } else {
                daysInMonthArray.add(
                    Data(name = (i - dayOfWeek).toString())
                )
            }
        }

        return daysInMonthArray
    }

    private fun monthYearFromDate(date: LocalDate) : String{
        val dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(dateTimeFormatter)
    }

    override fun onDateClick(data: Data, position: Int) {
        if (data.name != "") {
            val message =
                "Selected Date " + data.name.toString() + " " + monthYearFromDate(selectedDate)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}