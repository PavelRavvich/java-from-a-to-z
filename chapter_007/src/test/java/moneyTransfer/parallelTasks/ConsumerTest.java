package moneyTransfer.parallelTasks;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ConsumerTest {
    private static Consumer consumer = new Consumer();

    @BeforeClass
    public static void before() throws InterruptedException {
        for (int i = 100; i < 200; i++) {
            consumer.addAccount(new BigDecimal("32"), "name", i);
        }
    }

    @Test
    public void whenAddWithOriginalIdThenAddAccountReturnTrue() throws InterruptedException {
        List<Boolean> result = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            boolean a;
            result.add(a = consumer.addAccount(new BigDecimal("32"), "name", i));
            System.out.println(a);
        }

        result.forEach(Assert::assertTrue);
    }

    @Test
    public void whenAddWithNotOriginalIdThenAddAccountReturnFalse() throws InterruptedException {
        List<Boolean> result = new LinkedList<>();
        for (int i = 100; i < 150; i++) {
            consumer.addAccount(new BigDecimal("32"), "name", i);
        }

        result.forEach(Assert::assertFalse);
    }

    @Test
    public void whenDeleteAccountWhichExistThenDeleteAccountReturnTrue() throws InterruptedException {
        List<Boolean> result = new LinkedList<>();
        for (int i = 100; i < 200; i++) {
            result.add(consumer.delAccount(i));
        }

        result.forEach(Assert::assertTrue);
    }

    @Test
    public void whenDeleteAccountWhichNotExistThenDeleteAccountReturnFalse() throws InterruptedException {
        Consumer consumer = new Consumer();
        List<Boolean> result = new LinkedList<>();
        for (int i = 100; i < 200; i++) {
            result.add(consumer.delAccount(i));
        }

        result.forEach(Assert::assertFalse);
    }

    @Test
    public void thenManyThreadsGoThen() throws InterruptedException {
        Consumer consumer = new Consumer();
        List<Boolean> result = new LinkedList<>();
        for (int i = 100; i < 500; i++) {
            result.add(consumer.addAccount(new BigDecimal("32"), "name", i));
            result.add(consumer.delAccount(i));
        }

        result.forEach(Assert::assertTrue);
    }
}