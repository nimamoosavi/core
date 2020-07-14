package ir.webold.framework.utility;


import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationCalender {

    @Autowired
    PersianCalendar persianCalender;
    public static DateFormat dateFormat = null;
    public static DateFormat dateFormatWithTime = null;

    public static final String DEFAULT_PERSIAN_DATE_PATTERN_WITH_TIME = "yyyy/MM/dd hh:mm";

    public static final String DEFAULT_PERSIAN_DATE_PATTERN = "yyyy/MM/dd";

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
