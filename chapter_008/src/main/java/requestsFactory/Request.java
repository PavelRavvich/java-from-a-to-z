package requestsFactory;

public interface Request {

    String generate(final String condition, final String ... value);

}
