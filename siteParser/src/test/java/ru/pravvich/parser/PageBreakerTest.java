package ru.pravvich.parser;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class PageBreakerTest {

    @Test
    public void whenNextPageCallFirstTimeThenGetRootForum() {

        final PropertiesLoader properties = new PropertiesLoader("siteURL");
        final PageBreaker turner = new PageBreaker(properties);

        final String result = turner.nextPage();

        Assert.assertThat(result, is("http://www.sql.ru/forum/job-offers"));
    }

    @Test
    public void whenNextPageCallSecondTimeThenReturnPageBySlash1() {

        final PropertiesLoader properties = new PropertiesLoader("siteURL");
        final PageBreaker turner = new PageBreaker(properties);

        //First call.
        turner.nextPage();

        //Second call.
        final String result = turner.nextPage();

        Assert.assertThat(result, is("http://www.sql.ru/forum/job-offers/1"));
    }

    @Test
    public void whenNextPageCallTreedTimeThenReturnPageBySlash2() {
        final PropertiesLoader properties = new PropertiesLoader("siteURL");
        final PageBreaker turner = new PageBreaker(properties);

        //First call.
        turner.nextPage();

        //Second call.
        turner.nextPage();

        //Treed call.
        final String result = turner.nextPage();

        Assert.assertThat(result, is("http://www.sql.ru/forum/job-offers/2"));
    }

    @Test
    public void whenNextPageCallFourthTimeThenReturnPageBySlash3() {
        final PropertiesLoader properties = new PropertiesLoader("siteURL");
        final PageBreaker turner = new PageBreaker(properties);

        //First call.
        turner.nextPage();

        //Second call.
        turner.nextPage();

        //Treed call.
        turner.nextPage();

        //Fourth call.
        final String result = turner.nextPage();

        Assert.assertThat(result, is("http://www.sql.ru/forum/job-offers/3"));
    }

    @Test
    public void whenCallNextFirstTimeThenGetRootForum() {

        final PropertiesLoader properties = new PropertiesLoader("siteURL");
        final PageBreaker turner = new PageBreaker(properties);

        //First call.
        final Document document = turner.next();

        final String result = document.baseUri();

        Assert.assertThat(result, is("http://www.sql.ru/forum/job-offers"));

    }

    @Test
    public void whenNextCallSecondTimeThenReturnPageBySlash1() {
        final PropertiesLoader properties = new PropertiesLoader("siteURL");
        final PageBreaker turner = new PageBreaker(properties);

        //First call.
        turner.next();

        //Second call.
        final Document document = turner.next();

        final String result = document.baseUri();

        Assert.assertThat(result, is("http://www.sql.ru/forum/job-offers/1"));
    }

    @Test
    public void whenNextCallTreedTimeThenReturnPageBySlash2() {
        final PropertiesLoader properties = new PropertiesLoader("siteURL");
        final PageBreaker turner = new PageBreaker(properties);

        //First call.
        turner.next();

        //Second call.
        turner.next();

        //Treed call.
        final Document document = turner.next();

        final String result = document.baseUri();

        Assert.assertThat(result, is("http://www.sql.ru/forum/job-offers/2"));
    }
}