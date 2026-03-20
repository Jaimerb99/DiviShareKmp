package com.jrb.divishare.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.number

/**
 * Converts a date in the format "yyyy-MM-dd" to the format "dd/MM/yyyy".
 *
 * @param inputDate The date string in the format "yyyy-MM-dd".
 * @return A string representing the date in the format "dd/MM/yyyy".
 */
fun parseDate(inputDate: String): String {
    // Parse the input string into a LocalDate object. The input format is expected to be "yyyy-MM-dd".
    val date = LocalDate.parse(inputDate)

    // Format the date components (day, month, year) into the "dd/MM/yyyy" format.
    // padStart ensures that day and month always have two digits.
    return "${date.day.toString().padStart(2, '0')}/" +
            "${date.month.number.toString().padStart(2, '0')}/" +
            "${date.year}"
}