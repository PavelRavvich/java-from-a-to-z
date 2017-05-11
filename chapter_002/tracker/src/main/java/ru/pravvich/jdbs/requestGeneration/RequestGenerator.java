package ru.pravvich.jdbs.requestGeneration;

import ru.pravvich.jdbs.requestGeneration.requestBuilder.*;

import java.util.ArrayList;
import java.util.List;

public class RequestGenerator implements Generator {
    private final List<RequestBuilder> builders;

    public RequestGenerator() {

        this.builders = new ArrayList<>(100);

        this.initBuilders();
    }

    private void initBuilders() {
        this.builders.add(new AddItem());
        this.builders.add(new DBExists());
        this.builders.add(new AddCommit());
        this.builders.add(new DeleteItem());
        this.builders.add(new EditCommit());
        this.builders.add(new FindByHeader());
        this.builders.add(new DeleteCommit());
        this.builders.add(new GetterItemByID());
        this.builders.add(new CreateDatabase());
        this.builders.add(new AddDescription());
        this.builders.add(new UpdatedItemHeader());
    }

    @Override
    public String generate(final String typeRequest, final String ... conditions) {

        for (final RequestBuilder b : this.builders) {

            if (b.requestTypeEqual(typeRequest)) {

                return b.build(conditions);

            }

        }

        return "-1";
    }
}
