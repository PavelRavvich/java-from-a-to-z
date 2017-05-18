package ru.pravvich.parser;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeFormatConverterTest {
    @Test
    public void whenConvertOldDataThenGetTimestampEqualsValue() throws ParseException {
        final TimeFormatConverter converter = new TimeFormatConverter();
        final Timestamp result = converter.convert("28 мар 17, 14:29");
        Assert.assertTrue(result.toString().contains("2017-03-28 14:29"));
    }

    @Test
    public void whenConvertTodayThenInResultHadSameTime() throws ParseException {
        Calendar c = new GregorianCalendar();
        final String year = c.get(Calendar.YEAR) + "";
        final int month = c.get(Calendar.MONTH) + 1;
        String mon = ""+month;
        if (month < 10) {
            mon = "0" + month;
        }
        final int day = c.get(Calendar.DAY_OF_MONTH);

        String timestampFormatToday = year +"-"+ (mon)+"-"+ day +" 14:41";


        final TimeFormatConverter converter = new TimeFormatConverter();
        final Timestamp today = converter.convert("сегодня, 14:41");

        Assert.assertTrue(today.toString().contains(timestampFormatToday));
    }

    @Test
    public void whenConvertYesterdayThenInResultHadSameTime() {
        Calendar c = new GregorianCalendar();
        final long time = System.currentTimeMillis() - 86_400_000L;
        c.setTimeInMillis(time);

        final String year = c.get(Calendar.YEAR) + "";
        final int month = c.get(Calendar.MONTH) + 1;
        String mon = ""+month;
        if (month < 10) {
            mon = "0" + month;
        }
        final int day = c.get(Calendar.DAY_OF_MONTH);

        String timestampFormatYesterday = year +"-"+ (mon)+"-"+ day +" 14:41";


        final TimeFormatConverter converter = new TimeFormatConverter();
        final Timestamp today = converter.convert("вчера, 14:41");

        Assert.assertTrue(today.toString().contains(timestampFormatYesterday));
    }
}