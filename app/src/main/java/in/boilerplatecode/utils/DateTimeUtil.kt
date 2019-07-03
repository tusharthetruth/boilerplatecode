package `in`.boilerplatecode.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone


object DateTimeUtil {

    val SDF_ddMMMyy = SimpleDateFormat("dd MMM yyyy", Locale.US)
    val SDF_ddMMMyyy_hhmm = SimpleDateFormat("yyyy-MMM-dd hh:mm aa", Locale.US)
    val SDF_ddMMyyyy = SimpleDateFormat("dd-MM-yyyy", Locale.US)
    val SDF_yyyyMMdd = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val SDF_HHmm = SimpleDateFormat("HH:mm", Locale.US)
    val SDF_hhmma = SimpleDateFormat("hh:mm a", Locale.US)
    val SDF_yyyyMMdd_hhmm = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
    val SDF_UTC = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val SDF_CURRENT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US)

    val TIME_ZONE_IST = TimeZone.getTimeZone("Asia/Kolkata")
    val TIME_ZONE_UTC = TimeZone.getTimeZone("UTC")

    fun getCalender(daate: String?): Calendar? {
        var c: Calendar? = null
        if (daate == null || daate.equals("", ignoreCase = true) || daate.equals(
                null!!,
                ignoreCase = true
            ) || daate.equals("null", ignoreCase = true)
        )
            return null
        val df = SDF_CURRENT
        var d: Date? = null
        try {
            d = Date()
            d = df.parse(daate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        c = Calendar.getInstance(Locale.US)
        c!!.timeInMillis = d!!.time
        return c
    }


    fun getDueDateFromCalender(calendar: Calendar): String {
        var dateString = ""
        try {

            val destDf = SDF_ddMMMyyy_hhmm
            dateString = destDf.format(calendar.time)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return dateString
    }


    private fun getFormattedDate(format: SimpleDateFormat?, date: String?): String? {
        if (format == null || date == null) return null
        val c = DateTimeUtil.getCalender(date)
        return format.format(c!!.time)
    }
}
