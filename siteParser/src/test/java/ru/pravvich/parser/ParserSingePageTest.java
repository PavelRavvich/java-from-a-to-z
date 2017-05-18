package ru.pravvich.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.core.Is.is;

public class ParserSingePageTest {

    @Test
    public void whenHeaderContainWordJavaThenQueueHadElement() throws IOException, ParseException {
        String html = "<html><head><title>First parse</title></head> <body><table><tr> \n" +
                "<td class=\"icon_cell\"><img src=\"/forum/images/message.gif\" alt=\"\"></td> \n" +
                "<td class=\"postslisttopic\"> <a href=\"url_propose\">test_header Java</a> &nbsp; " +
                "<a class=\"newTopic\" href=\"http://www.some_site.ru/forum/actualutils.aspx?action=" +
                "go tonew&amp;tid=1185343\">[new]</a> </td> <td class=\"altCol\"> <a href=\"url_autho" +
                "r\"> test_name_author </a> </td> <td style=\"text-align:center\">13</td> <td style=\"" +
                "text-align:center\">1531</td> <td style=\"text-align:center\" class=\"altCol\">9 май 17" +
                ", 00:25</td></tr></table></body></html>";


        final Document document = Jsoup.parse(html);

        final Queue<Proposal> proposals = new LinkedList<>();
        final TimeFormatConverter converter = new TimeFormatConverter();
        final ProposalValidatorUtil validator = new ProposalValidatorUtil();

        ParserSingePage parser = new ParserSingePage(proposals, converter, validator);

        parser.parseDocument(document);

        final Proposal result = proposals.poll();

        Assert.assertThat(result.getHeader(), is("test_header Java"));
        Assert.assertThat(result.getNickname(), is("test_name_author"));
        Assert.assertThat(result.getUlrPropose(), is("url_propose"));
        Assert.assertThat(result.getUrlRecruiter(), is("url_author"));
        Assert.assertThat(result.getCreateTime().toString(), is("2017-05-09 00:25:00.0"));
    }

    @Test
    public void whenInHTMLContainsTwoValidProposesThenQueueSizeIsTwo() {

        String html = "<html><head><title>First parse</title></head> <body><table><tr> \n" +
                "<td class=\"icon_cell\"><img src=\"/forum/images/message.gif\" alt=\"\"></td> \n" +
                "<td class=\"postslisttopic\"> <a href=\"url_propose\">test_header Java</a> &nbsp; " +
                "<a class=\"newTopic\" href=\"http://www.some_site.ru/forum/actualutils.aspx?action=" +
                "go tonew&amp;tid=1185343\">[new]</a> </td> <td class=\"altCol\"> <a href=\"url_autho" +
                "r\"> test_name_author </a> </td> <td style=\"text-align:center\">13</td> <td style=\"" +
                "text-align:center\">1531</td> <td style=\"text-align:center\" class=\"altCol\">9 май 17" +
                ", 00:25</td></tr><tr>" +
                "<td class=\"icon_cell\"><img src=\"/forum/images/message.gif\" alt=\"\"></td> \n" +
                "<td class=\"postslisttopic\"> <a href=\"url_propose\">test_header Java</a> &nbsp; " +
                "<a class=\"newTopic\" href=\"http://www.some_site.ru/forum/actualutils.aspx?action=" +
                "go tonew&amp;tid=1185343\">[new]</a> </td> <td class=\"altCol\"> <a href=\"url_autho" +
                "r\"> test_name_author </a> </td> <td style=\"text-align:center\">13</td> <td style=\"" +
                "text-align:center\">1531</td> <td style=\"text-align:center\" class=\"altCol\">9 май 17" +
                ", 00:25</td></tr>"+"</table></body></html>";


        final Document document = Jsoup.parse(html);

        final Queue<Proposal> proposals = new LinkedList<>();
        final TimeFormatConverter converter = new TimeFormatConverter();
        final ProposalValidatorUtil validator = new ProposalValidatorUtil();

        ParserSingePage parser = new ParserSingePage(proposals, converter, validator);

        parser.parseDocument(document);

        final int result = proposals.size();

        Assert.assertThat(result, is(2));
    }

    @Test
    public void whenHeaderNotValidThenQueueIsEmpty() {

        String html = "<html><head><title>First parse</title></head> <body><table><tr> \n" +
                "<td class=\"icon_cell\"><img src=\"/forum/images/message.gif\" alt=\"\"></td> \n" +
                "<td class=\"postslisttopic\"> <a href=\"url_propose\">Java Script</a> &nbsp; " +
                "<a class=\"newTopic\" href=\"http://www.some_site.ru/forum/actualutils.aspx?action=" +
                "go tonew&amp;tid=1185343\">[new]</a> </td> <td class=\"altCol\"> <a href=\"url_autho" +
                "r\"> test_name_author </a> </td> <td style=\"text-align:center\">13</td> <td style=\"" +
                "text-align:center\">1531</td> <td style=\"text-align:center\" class=\"altCol\">9 май 17" +
                ", 00:25</td></tr></table></body></html>";


        final Document document = Jsoup.parse(html);

        final Queue<Proposal> proposals = new LinkedList<>();
        final TimeFormatConverter converter = new TimeFormatConverter();
        final ProposalValidatorUtil validator = new ProposalValidatorUtil();

        ParserSingePage parser = new ParserSingePage(proposals, converter, validator);

        parser.parseDocument(document);

        final Proposal result = proposals.poll();

        Assert.assertNull(result);
    }
}