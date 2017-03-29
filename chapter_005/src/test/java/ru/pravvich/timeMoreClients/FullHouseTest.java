package ru.pravvich.timeMoreClients;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FullHouseTest {
    @Test
    public void whenStartFstLessFinishSndThen() {
        TimeZoneCline zone01 = new TimeZoneCline(13.00f, 15.00f);
        TimeZoneCline zone02 = new TimeZoneCline(12.00f, 14.00f);
        List<TimeZoneCline> zones = new ArrayList<>();
        zones.add(zone01);
        zones.add(zone02);

        FullHouse fullHouse = new FullHouse();
        TimeZoneCline result = fullHouse.getFullHouse(zones);

        assertThat(result.getStart(), is(13.00f));
        assertThat(result.getFinish(), is(14.00f));
    }

    @Test
    public void whenStartSndLessFinishFstThen() {
        TimeZoneCline zone01 = new TimeZoneCline(12.00f, 14.00f);
        TimeZoneCline zone02 = new TimeZoneCline(13.00f, 15.00f);
        List<TimeZoneCline> zones = new ArrayList<>();
        zones.add(zone01);
        zones.add(zone02);

        FullHouse fullHouse = new FullHouse();
        TimeZoneCline result = fullHouse.getFullHouse(zones);

        assertThat(result.getStart(), is(13.00f));
        assertThat(result.getFinish(), is(14.00f));
    }

    @Test
    public void whenTimeStartSameAndFirstOutEarlyThen() {
        TimeZoneCline zone01 = new TimeZoneCline(12.00f, 14.00f);
        TimeZoneCline zone02 = new TimeZoneCline(12.00f, 15.00f);
        List<TimeZoneCline> zones = new ArrayList<>();
        zones.add(zone01);
        zones.add(zone02);

        FullHouse fullHouse = new FullHouse();
        TimeZoneCline result = fullHouse.getFullHouse(zones);

        assertThat(result.getStart(), is(12.00f));
        assertThat(result.getFinish(), is(14.00f));
    }

    @Test
    public void whenTimeStartSameAndSecondOutFirst() {
        TimeZoneCline zone01 = new TimeZoneCline(12.00f, 15.00f);
        TimeZoneCline zone02 = new TimeZoneCline(12.00f, 14.00f);
        List<TimeZoneCline> zones = new ArrayList<>();
        zones.add(zone01);
        zones.add(zone02);

        FullHouse fullHouse = new FullHouse();
        TimeZoneCline result = fullHouse.getFullHouse(zones);

        assertThat(result.getStart(), is(12.00f));
        assertThat(result.getFinish(), is(14.00f));
    }

    @Test
    public void whenStartAndFinishSameThen() {
        TimeZoneCline zone01 = new TimeZoneCline(12.00f, 14.00f);
        TimeZoneCline zone02 = new TimeZoneCline(12.00f, 14.00f);
        List<TimeZoneCline> zones = new ArrayList<>();
        zones.add(zone01);
        zones.add(zone02);

        FullHouse fullHouse = new FullHouse();
        TimeZoneCline result = fullHouse.getFullHouse(zones);

        assertThat(result.getStart(), is(12.00f));
        assertThat(result.getFinish(), is(14.00f));
    }

    @Test
    public void whenMoreTwoClientsInThen() {
        TimeZoneCline zone01 = new TimeZoneCline(12.00f, 15.00f);
        TimeZoneCline zone02 = new TimeZoneCline(13.00f, 17.00f);
        TimeZoneCline zone03 = new TimeZoneCline(14.00f, 18.00f);
        List<TimeZoneCline> zones = new ArrayList<>();
        zones.add(zone01);
        zones.add(zone02);
        zones.add(zone03);

        FullHouse fullHouse = new FullHouse();
        TimeZoneCline result = fullHouse.getFullHouse(zones);

        assertThat(result.getStart(), is(14.00f));
        assertThat(result.getFinish(), is(15.00f));
    }

    @Test
    public void whenManyClientsThen() {
        TimeZoneCline zone05 = new TimeZoneCline(11.00f, 13.00f);
        TimeZoneCline zone03 = new TimeZoneCline(14.00f, 18.00f);
        TimeZoneCline zone01 = new TimeZoneCline(12.00f, 15.00f);
        TimeZoneCline zone04 = new TimeZoneCline(14.00f, 17.00f);
        TimeZoneCline zone02 = new TimeZoneCline(13.00f, 17.00f);

        List<TimeZoneCline> zones = new ArrayList<>();
        zones.add(zone05);
        zones.add(zone03);
        zones.add(zone01);
        zones.add(zone02);
        zones.add(zone04);

        FullHouse fullHouse = new FullHouse();
        TimeZoneCline result = fullHouse.getFullHouse(zones);
        System.out.println(result.toString());
        assertThat(result.getStart(), is(14.00f));
        assertThat(result.getFinish(), is(15.00f));
    }
}