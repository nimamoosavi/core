package ir.webold.framework.mapper;

import ir.webold.framework.domain.entity.BaseEntity;
import ir.webold.framework.utility.ApplicationCalender;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface GeneralMapper<T extends BaseEntity, R, S> {

    T requestToEntity(R source);

    R toRequestModel(T target);

    List<T> requestToEntity(List<R> sourceList);

    List<S> toRequestModel(List<R> targetList);

    T responseToEntity(S source);

    S toResponseModel(T target);

    List<T> responseToEntity(List<S> sourceList);

    List<S> toResponseModel(List<T> targetList);


    @Named("stringToDate")
    default Date stringToDate(String source) throws ParseException {
        return ApplicationCalender.getDateFormat().parse(source);
    }

    @Named("stringToDateWithTime")
    default Date stringToDateWithTime(String source) throws ParseException {
        return ApplicationCalender.getDateFormatWithTime().parse(source);
    }

    @Named("stringToTimestamp")
    default Timestamp stringToTimestamp(String source) throws ParseException {
        return new Timestamp(ApplicationCalender.getDateFormat().parse(source).getTime());
    }

    @Named("stringToTimestampWithTime")
    default Timestamp stringToTimestampWithTime(String source) throws ParseException {
        return new Timestamp(ApplicationCalender.getDateFormatWithTime().parse(source).getTime());
    }

    @Named("dateToString")
    default String dateToString(Date source) {
        return ApplicationCalender.getDateFormat().format(source);
    }

    @Named("timestampToString")
    default String timestampToString(Timestamp source) {
        return ApplicationCalender.getDateFormat().format(source);
    }

    @Named("dateWithTimeToString")
    default String dateWithTimeToString(Date source) {
        return ApplicationCalender.getDateFormatWithTime().format(source);
    }

    @Named("timestampWithTimeToString")
    default String timestampWithTimeToString(Timestamp source) {
        return ApplicationCalender.getDateFormatWithTime().format(source);
    }
}
