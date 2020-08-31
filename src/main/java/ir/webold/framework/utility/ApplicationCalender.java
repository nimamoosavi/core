package ir.webold.framework.utility;


import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;

import static ir.webold.framework.config.general.GeneralStatic.DEFAULT_PERSIAN_DATE_PATTERN;
import static ir.webold.framework.config.general.GeneralStatic.DEFAULT_PERSIAN_DATE_PATTERN_WITH_TIME;

public class ApplicationCalender {

    private ApplicationCalender() {
    }

    private static DateFormat dateFormat = null;
    private static DateFormat dateFormatWithTime = null;


    public static DateFormat getDateFormat() {
        if (dateFormat == null) {
            dateFormat = getDateFormat(DEFAULT_PERSIAN_DATE_PATTERN);
        }
        return dateFormat;
    }

    public static DateFormat getDateFormatWithTime() {
        if (dateFormatWithTime == null) {
            dateFormatWithTime = getDateFormat(DEFAULT_PERSIAN_DATE_PATTERN_WITH_TIME);
        }
        return dateFormatWithTime;
    }


    private static DateFormat getDateFormat(String pattern) {
        PersianCalendar persianCalendar = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        DateFormat dateFormat = persianCalendar.getDateTimeFormat(DateFormat.FULL, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        ((SimpleDateFormat) dateFormat).applyPattern(pattern);
        return dateFormat;
    }

}
