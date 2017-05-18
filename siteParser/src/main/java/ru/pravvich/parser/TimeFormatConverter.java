package ru.pravvich.parser;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.lang.Integer.parseInt;

/**
 * Converted for date and time in timestamp type.
 * From: "сегодня, 14:41", "вчера, 14:41", 28 мар 17, 14:29".
 */
public class TimeFormatConverter {
    /**
     * Formatter for types "28 мар 17, 14:29".
     */
    final private SimpleDateFormat format;

    /**
     * Default constructor.
     */
    public TimeFormatConverter() {
        this.format = new SimpleDateFormat("dd MMM yy, HH:mm");
    }

    /**
     * Converting datetime in Timestamp.
     *
     * @param data for converting.
     * @return same datetime how Timestamp object.
     */
    Timestamp convert(final String data) {

        if (data.contains("сегодня")) return this.todayConverter(data);

        if (data.contains("вчера")) return this.yesterdayConverter(data);


        try {

            return new Timestamp(this.format.parse(data).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Timestamp(0L);
    }

    /**
     * Converter for types how : "сегодня, 14:41".
     *
     * @param data String value for convert.
     * @return datetime how Timestamp object.
     */
    private Timestamp todayConverter(final String data) {

        final String[] time = getTime(data);

        final Calendar current = new GregorianCalendar();

        current.set(
                current.get(Calendar.YEAR),
                current.get(Calendar.MONTH),
                current.get(Calendar.DAY_OF_MONTH),
                parseInt(time[0]),
                parseInt(time[1]));


        return new Timestamp(current.getTimeInMillis());
    }

    /**
     * Converter for types how : "вчера, 14:41".
     *
     * @param data String value for convert.
     * @return datetime how Timestamp object.
     */
    private Timestamp yesterdayConverter(final String data) {

        return new Timestamp(todayConverter(data).getTime() - 86_400_000L);

    }

    /**
     * Get time from string with time and data.
     *
     * @param data for parse.
     * @return array where by 0 index = hours, by 1 index = minutes.
     */
    private String[] getTime(final String data) {

        final String[] arr = data.split(" ");

        return arr[1].split(":");
    }
}
