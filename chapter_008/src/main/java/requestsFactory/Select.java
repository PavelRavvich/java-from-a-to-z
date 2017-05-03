package requestsFactory;

import requestsFactory.converter.Converter;
import requestsFactory.converter.RegExpUtil;
import requestsFactory.converter.SelectAllUtil;
import requestsFactory.converter.TaskWithCommentUtil;

import java.util.ArrayList;
import java.util.List;

class Select implements Request {
    /**
     * All converters.
     */
    final private List<Converter> converters;

    /**
     * Default constructor.
     */
    public Select() {
        this.converters = new ArrayList<>(50);
        initConverters();
    }

    /**
     * Init all converters.
     * If necessary may de add new converters, without problem with old test.
     */
    private void initConverters() {
        this.converters.add(new TaskWithCommentUtil());
        this.converters.add(new SelectAllUtil());
        this.converters.add(new RegExpUtil());
    }

    /**
     * Command design patterns.
     * Find Converter by condition value.
     * @param condition flag for choice converter.
     * @param value see in implementation converters.
     * @return request in Postgres style.
     */
    @Override
    public String generate(final String condition, final String ... value) {

        for (Converter c : this.converters) {

            if (c.typeEquals(condition)) {

                return c.convertToPostgres(value);

            }

        }

        return "undefined";
    }

}
