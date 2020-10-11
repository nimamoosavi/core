package ir.webold.framework.config.general;

public final class GeneralStatic {

    private GeneralStatic() {
        throw new IllegalStateException("GeneralStatic class");
    }

    public static final String RRN = "rrn";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BASIC = "Basic ";
    public static final String DEFAULT_PERSIAN_DATE_PATTERN_WITH_TIME = "yyyy/MM/dd hh:mm";
    public static final String DEFAULT_PERSIAN_DATE_PATTERN = "yyyy/MM/dd";
    public static final String RABBIT_LOG_OUT_EXCHANGE = "global.logout.exchange";
}
