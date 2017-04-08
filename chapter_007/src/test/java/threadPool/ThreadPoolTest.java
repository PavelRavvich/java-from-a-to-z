package threadPool;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.core.Is.is;

public class ThreadPoolTest {
    @Test
    public void whenThreadPoolWorkThenAllThreadIsRun() {
        ThreadPool serves = new ThreadPool();
        List<Integer> result = new CopyOnWriteArrayList<>();

        for (int j = 0; j < 7; j++) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    result.add(1);
                }
            };
            serves.add(r);
        }

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        serves.shutdown();

        Assert.assertThat(result.size(), is(7));
        Assert.assertThat(serves.isShutdown(), is(true));
    }
}