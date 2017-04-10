package nonBlockingCash.taskModel;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ModelTask implements Task {
    private final AtomicInteger id;
    private final AtomicReference<String> name;

    private final AtomicInteger version;

    public ModelTask(final int id, final String name) {
        this.id = new AtomicInteger(id);
        this.name = new AtomicReference<>(name);
        this.version = new AtomicInteger(1);
    }

    public ModelTask(final Task task) {
        this.id = new AtomicInteger(task.getId());
        this.name = new AtomicReference<>(task.getName());
        this.version = new AtomicInteger(task.getVersion());
    }

    @Override
    public int getId() {
        int temp;
        temp = id.get();
        return temp;
    }

    @Override
    public void setId(final int id) {
        this.id.set(id);
    }

    @Override
    public String getName() {
        String temp;
        temp = name.get();
        return temp;
    }

    @Override
    public void setName(final String name) {
        this.name.set(name);
    }

    @Override
    public int getVersion() {
        int temp;
        temp = version.get();
        return temp;
    }

    @Override
    public void incrementVersion() {
        version.incrementAndGet();
    }

    @Override
    public void decrementVersion() {
        version.decrementAndGet();
    }

    @Override
    public String toString() {
        return "ModelTask{" +
                "id=" + id.get() +
                ", name=" + name.get() +
                ", version=" + version.get() +
                '}';
    }
}
