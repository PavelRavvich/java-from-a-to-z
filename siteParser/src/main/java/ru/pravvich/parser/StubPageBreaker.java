package ru.pravvich.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Use for test ru.pravvich.timing.ByTheDayController.class.
 */
public class StubPageBreaker extends PageBreaker {
    /**
     * Default constructor.
     *
     * @param properties with url to website for parse.
     */
    public StubPageBreaker(PropertiesLoader properties) {
        super(properties);
    }

    private String header00 = "test_header_Java_00";

    private String header01 = "test_header_Java_01";

    private String header02 = "test_header_Java 02";

    @Override
    public Document next() {

        String html = "<html><head><title>First parse</title></head> <body><table>" +


                "<tr><td class=\"icon_cell\"><img src=\"/forum/images/message.gif\" alt=\"\"></td> \n" +
                "<td class=\"postslisttopic\"> <a href=\"url_propose\">"+header00+"</a> &nbsp; " +
                "<a class=\"newTopic\" href=\"http://www.some_site.ru/forum/actualutils.aspx?action=" +
                "go tonew&amp;tid=1185343\">[new]</a> </td> <td class=\"altCol\"> <a href=\"url_autho" +
                "r\"> test_name_author </a> </td> <td style=\"text-align:center\">13</td> <td style=\"" +
                "text-align:center\">1531</td> <td style=\"text-align:center\" class=\"altCol\">9 май 18" +
                ", 00:25</td></tr>" +


                "<tr><td class=\"postslisttopic\"> <a href=\"url_propose\">"+header01+"</a> &nbsp; " +
                "<a class=\"newTopic\" href=\"http://www.some_site.ru/forum/actualutils.aspx?action1=" +
                "go tonew&amp;tid=1185343\">[new]</a> </td> <td class=\"altCol\"> <a href=\"url_autho" +
                "r\"> test_name_author </a> </td> <td style=\"text-align:center\">13</td> <td style=\"" +
                "text-align:center\">1531</td> <td style=\"text-align:center\" class=\"altCol\">8 май 18" +
                ", 00:25</td></tr>"+


                "<tr><td class=\"icon_cell\"><img src=\"/forum/images/message.gif\" alt=\"\"></td> \n" +
                "<td class=\"postslisttopic\"> <a href=\"url_propose_02\">"+header02+"</a> &nbsp; " +
                "<a class=\"newTopic\" href=\"http://www.some_site.ru/forum/actualutils.aspx?action2" +
                "go tonew&amp;tid=1185343\">[new]</a> </td> <td class=\"altCol\"> <a href=\"url_autho" +
                "r\"> test_name_author </a> </td> <td style=\"text-align:center\">13</td> <td style=\"" +
                "text-align:center\">1531</td> <td style=\"text-align:center\" class=\"altCol\">13 май 16" +
                ", 00:25</td></tr>"+

                "</table></body></html>";


        return Jsoup.parse(html);
    }
}
