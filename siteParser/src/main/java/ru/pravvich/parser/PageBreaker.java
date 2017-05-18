package ru.pravvich.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Switcher to forum pages.
 */
public class PageBreaker {
    /**
     * URL to website. Change every time when call this.nextPage().
     */
    private String url;
    /**
     * Counter for calls this.nextPage().
     */
    private int counter;
    /**
     * Connection to database.
     */
    private Connection connection;

    /**
     * Default constructor.
     *
     * @param properties with url to website for parse.
     */
    public PageBreaker(final PropertiesLoader properties) {

        this.url = properties.getValue("url");

    }

    /**
     * For getConnection getNext document.
     *
     * @return getNext page document. If throw IOException return empty Document.
     */
    public Document next() {
        try {

            if (connection == null) {

                connection = Jsoup.connect(nextPage());

                return connection.get();

            }

            return connection.url(nextPage()).get();


        } catch (IOException e) {
            e.printStackTrace();
        }


        return new Document("");
    }

    /**
     * Switch-over to getNext forum page.
     *
     * @return page ulr for parse.
     */
    String nextPage() {

        //First call.
        if (counter == 0) {
            counter++;
            return url;
        }

        //Second call.
        if (counter == 1) {
            counter++;
            return url = String.format("%s/1", url);
        }

        //Always after second call.
        final String[] arr = url.split("/");

        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length - 1; i++) {
            sb.append(arr[i]).append("/");
        }

        sb.append(counter++);


        return new String(sb);
    }

    public void resetPageCounter() {
        counter = 0;
    }
}
