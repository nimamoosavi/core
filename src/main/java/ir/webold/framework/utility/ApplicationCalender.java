package ir.webold.framework.utility;


import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;
import org.springframework.stereotype.Component;

import static ir.webold.framework.config.general.GeneralStatic.DEFAULT_PERSIAN_DATE_PATTERN;
import static ir.webold.framework.config.general.GeneralStatic.DEFAULT_PERSIAN_DATE_PATTERN_WITH_TIME;

@Component
public class ApplicationCalender {

    public static DateFormat DATEFORMAT = null;
    public static DateFormat DATEFORMATWITHTIME = null;


    public static DateFormat getDateFormat() {
        if (DATEFORMAT == null) {
            DATEFORMAT = getDateFormat(DEFAULT_PERSIAN_DATE_PATTERN);
        }
        return DATEFORMAT;
    }

    public static DateFormat getDateFormatWithTime() {
        if (DATEFORMATWITHTIME == null) {
            DATEFORMATWITHTIME = getDateFormat(DEFAULT_PERSIAN_DATE_PATTERN_WITH_TIME);
        }
        return DATEFORMATWITHTIME;
    }


    private static DateFormat getDateFormat(String pattern) {
        PersianCalendar persianCalendar = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        DateFormat dateFormat = persianCalendar.getDateTimeFormat(DateFormat.FULL, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        ((SimpleDateFormat) dateFormat).applyPattern(pattern);
        return dateFormat;
    }

}
