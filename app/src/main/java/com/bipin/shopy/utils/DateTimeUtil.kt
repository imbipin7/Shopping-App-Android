package com.bipin.shopy.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.text.format.DateUtils
import com.bipin.shopy.MainActivity
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateTimeUtil {

    const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val TIME_ZONE_UTC = "UTC"
    const val TIME_ZONE_GMT = "GMT"
    const val UTC_DATE_FORMAT ="yyyy-MM-dd HH:mm:ss"

    /**From Local to UTC*/
    fun getTimeInUTCs(milliSec: Long, requiredFormat: String = DATE_FORMAT): String? {
        return try {
            val simple = SimpleDateFormat(requiredFormat, Locale.getDefault())
            simple.timeZone = TimeZone.getTimeZone(TIME_ZONE_UTC)
            val result = Date(milliSec)
            simple.format(result)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getTimeInUTC(
        date: String?,
        inputFormat: String = DATE_FORMAT,
        outputFormat: String = DATE_FORMAT
    ): String? {
        return try {
            if (date.isNullOrBlank())
                return null
            val inputDateFormatter = SimpleDateFormat(inputFormat, Locale.getDefault())
            inputDateFormatter.timeZone = TimeZone.getTimeZone(TIME_ZONE_UTC)
            val utcDate = inputDateFormatter.parse(date)

            val outputDateFormatter = SimpleDateFormat(outputFormat, Locale.getDefault())
            if (utcDate == null)
                return null
            outputDateFormatter.format(utcDate)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**Change Time Format*/
    fun changeTimeFormat(
        input: String?,
        inputFormat: String = DATE_FORMAT,
        outputFormat: String = DATE_FORMAT
    ): String? {
        if (input.isNullOrBlank() || inputFormat.equals(outputFormat, true)) return null

        return try {
            val date = SimpleDateFormat(inputFormat, Locale.getDefault())
            val formattedDate = date.parse(input)
            val simpleDateFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
            simpleDateFormat.format(formattedDate)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun changeTimeFormat(input: Long?, outputFormat: String = DATE_FORMAT): String? {
        if (input == null) return null

        return try {
            val simpleDateFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
            simpleDateFormat.format(input)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getAge(
        dob: String?,
        inputFormat: String = DATE_FORMAT
    ): Int? {
        if (dob == null) return null
        return try {
            val simpleDateFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
            val date = simpleDateFormat.parse(dob) ?: return null

            val dateOfBirthDay = Calendar.getInstance()
            dateOfBirthDay.time = date
            val currentDay = Calendar.getInstance()
            var age = currentDay.get(Calendar.YEAR) - dateOfBirthDay.get(Calendar.YEAR)
            if (currentDay.get(Calendar.DAY_OF_YEAR) < dateOfBirthDay.get(Calendar.DAY_OF_YEAR))
                age -= 1
            age
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getTimeAgo(
        date: String?,
        inputFormat: String = DATE_FORMAT,
        timeZoned: Boolean = false
    ): String {
        if (date.isNullOrBlank())
            return ""

        val sdf = SimpleDateFormat(inputFormat, Locale.getDefault())

        if (timeZoned)
            sdf.timeZone = TimeZone.getTimeZone(TIME_ZONE_UTC)
        else
            sdf.timeZone = TimeZone.getTimeZone(TIME_ZONE_GMT)
        return try {
            val time = sdf.parse(date).time
            val now = System.currentTimeMillis()
            return when (val ago =
                DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)) {
                "0 minutes ago" -> {
                    "few seconds ago"
                }
                "In 0 minutes" -> {
                    "few minutes ago"
                }
                else -> {
                    ago.toString()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    /**DATE PICKER*/
    fun datePickerDialog(
        isMaxCalendar: Boolean = false,
        isMinCalendar: Boolean = false,
        maxDate: Calendar = Calendar.getInstance(),
        minDate: Calendar = Calendar.getInstance(),
        selectedDate: Calendar = Calendar.getInstance(),
        returnDate: (Calendar) -> Unit
    ) {
        val datePicker = DatePickerDialog(
            MainActivity.context.get()!!,
            { _, year, month, dayOfMonth ->

                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, month)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                returnDate(selectedDate)
            }, selectedDate
                .get(Calendar.YEAR), selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        )
        if (isMaxCalendar) datePicker.datePicker.maxDate = maxDate.timeInMillis
        if (isMinCalendar) datePicker.datePicker.minDate = minDate.timeInMillis
        datePicker.show()
    }

    /**TIME PICKER*/
    fun timePickerDialog(
        selectedDate: Calendar = Calendar.getInstance(),
        returnDate: (Calendar) -> Unit
    ) {
        val timePicker = TimePickerDialog(
            MainActivity.context.get(),
            { _, hour, minute ->
                selectedDate.set(Calendar.HOUR_OF_DAY, hour)
                selectedDate.set(Calendar.MINUTE, minute)
                returnDate(selectedDate)
            }, selectedDate.get(Calendar.HOUR_OF_DAY), selectedDate.get(Calendar.MINUTE), false
        )
        timePicker.show()
    }


    /**Get Difference between dates*/
    fun getDatesDifference(
        startDate: String?,
        endDate: String? = null,
        startDateFormat: String = DATE_FORMAT,
        endDateFormat: String = DATE_FORMAT
    ): Calendar? {
        if (startDate.isNullOrBlank())
            return null

        val startFormatter = SimpleDateFormat(startDateFormat, Locale.getDefault())
        val startingDate = startFormatter.parse(startDate)?.time ?: return null

        if (endDate.isNullOrBlank()) {
            val diff = Calendar.getInstance()
            diff.timeInMillis = diff.timeInMillis - startingDate
            return diff
        }
        val endFormatter = SimpleDateFormat(endDateFormat, Locale.getDefault())
        val endingDate = endFormatter.parse(endDate)?.time ?: return null
        val diff = Calendar.getInstance()
        diff.timeInMillis = endingDate - startingDate
        return diff
    }


    @SuppressLint("SimpleDateFormat")
    fun getTimeBooking(input: String?): String {
        var output = ""
        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").apply {
                timeZone = TimeZone.getTimeZone("GMT")
            }.parse(input)
            output = SimpleDateFormat("HH:mm a").format(simpleDateFormat!!)
        } catch (e: Exception) {
        }
        return output
    }


    fun getMinSec(millis: Long): String = String.format(
        "%02d.%02d",
        (TimeUnit.MILLISECONDS.toSeconds(millis)),
        ((millis % 1000).toString().take(2)).toLong()


    )

}