package fileSearch;

import java.io.File;

public class ConsumerOfSearch implements ConsumerGetter<File> {
    private File result = null;

    @Override
    public void accept(File result) {
        this.result = result;
    }

    @Override
    public File getResult() {
        return result;
    }
}
