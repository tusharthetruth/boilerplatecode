package in.boilerplatecode.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class DateTimeUtil {

    public static final SimpleDateFormat SDF_ddMMMyy = new SimpleDateFormat("dd MMM yyyy", Locale.US);
    public static final SimpleDateFormat SDF_ddMMMyyy_hhmm = new SimpleDateFormat("yyyy-MMM-dd hh:mm aa", Locale.US);
    public static final SimpleDateFormat SDF_ddMMyyyy = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    public static final SimpleDateFormat SDF_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    public static final SimpleDateFormat SDF_HHmm = new SimpleDateFormat("HH:mm", Locale.US);
    public static final SimpleDateFormat SDF_hhmma = new SimpleDateFormat("hh:mm a", Locale.US);
    public static final SimpleDateFormat SDF_yyyyMMdd_hhmm = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
    public static final SimpleDateFormat SDF_UTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
    public static final SimpleDateFormat SDF_CURRENT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);

    public static final TimeZone TIME_ZONE_IST = TimeZone.getTimeZone("Asia/Kolkata");
    public static final TimeZone TIME_ZONE_UTC = TimeZone.getTimeZone("UTC");

    public static Calendar getCalender(String daate) {
        Calendar c = null;
        if (daate == null || daate.equalsIgnoreCase("") || daate.equalsIgnoreCase(null) || daate.equalsIgnoreCase("null"))
            return null;
        DateFormat df = SDF_CURRENT;
        Date d = null;
        try {
            d = new Date();
            d = df.parse(daate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c = Calendar.getInstance(Locale.US);
        c.setTimeInMillis(d.getTime());
        return c;
    }


    public static String getDueDateFromCalender(Calendar calendar) {
        String dateString = "";
        try {

            SimpleDateFormat destDf = SDF_ddMMMyyy_hhmm;
            dateString = destDf.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }


    private static String getFormattedDate(SimpleDateFormat format, String date) {
        if (format == null || date == null) return null;
        Calendar c = DateTimeUtil.getCalender(date);
        return format.format(c.getTime());
    }
}
