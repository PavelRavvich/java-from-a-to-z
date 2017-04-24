package foursquare;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FigureTest {
    @Test
    public void whenFigureIsFoursquareCase1Then() {
        Figure figure = new Figure(
                new Point(0,0),
                new Point(0,2),
                new Point(2,2),
                new Point(2,0));

        final boolean result = figure.isFoursquare();
        assertThat(result, is(true));
    }

    @Test
    public void whenFigureIsFoursquareCase2Then() {
        Figure figure = new Figure(
                new Point(2,2),
                new Point(0,2),
                new Point(0,0),
                new Point(2,0));

        final boolean result = figure.isFoursquare();
        assertThat(result, is(true));
    }

    @Test
    public void whenFigureIsFoursquareCase3Then() {
        Figure figure = new Figure(
                new Point(2,2),
                new Point(0,0),
                new Point(0,2),
                new Point(2,0));

        final boolean result = figure.isFoursquare();
        assertThat(result, is(true));
    }

    @Test
    public void whenFigureIsRhombusWithNotEqualsDiagonalsThenReturnFalse() {
        Figure figure = new Figure(
                new Point(1,5),
                new Point(4,7),
                new Point(4,3),
                new Point(7,5));

        final boolean result = figure.isFoursquare();
        assertThat(result, is(false));
    }

    @Test
    public void whenFigureIsRectangleThenReturnFalse() {
        Figure figure = new Figure(
                new Point(0,0),
                new Point(0,2),
                new Point(3,0),
                new Point(3,2));

        final boolean result = figure.isFoursquare();
        assertThat(result, is(false));
    }
}