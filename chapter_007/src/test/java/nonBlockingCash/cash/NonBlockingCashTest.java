package nonBlockingCash.cash;

import nonBlockingCash.taskModel.ModelTask;
import nonBlockingCash.taskModel.Task;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class NonBlockingCashTest {
    @Test
    public void whenAdditionSuccessfullyTaskThenResultTrue() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final Task task1 = new ModelTask(1, "name1");
        final boolean result = cash.add(task1.getId(), task1);
        Assert.assertThat(result, is(true));
    }

    @Test
    public void whenAdditionSecondSameTaskThenReturnFalse() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final Task task1 = new ModelTask(1, "name1");
        cash.add(task1.getId(), task1);
        final boolean result = cash.add(task1.getId(), task1);
        Assert.assertThat(result, is(false));
    }

    @Test
    public void whenUpdatedTaskSuccessfullyReplaceOldTaskThenReturnTrue() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final Task task1 = new ModelTask(1, "name1");
        cash.add(task1.getId(), task1);
        final Task task1version2 = new ModelTask(1, "name1");
        final boolean result = cash.update(task1version2.getId(), task1version2);
        Assert.assertThat(result, is(true));
    }

    @Test(expected = DataIsLostException.class)
    public void whenVersionOldTaskAndUpdatedTaskNotEqualsThenTrowException() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final Task task1 = new ModelTask(1, "name1");
        cash.add(task1.getId(), task1);
        final Task task1version2 = new ModelTask(1, "name1");
        task1version2.incrementVersion();
        cash.update(task1version2.getId(), task1version2);
    }

    @Test
    public void whenGetTaskWhichExistThenReturnTask() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final Task task = new ModelTask(1, "name1");
        cash.add(task.getId(), task);
        final Task result = cash.get(task.getId(), task);
        Assert.assertThat(result, is(task));
    }

    @Test(expected = DataIsLostException.class)
    public void whenTryGetTaskWhichNotExistThenTrowException() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        cash.get(1, new ModelTask(1, ""));
    }

    @Test
    public void whenDeleteTaskWhichExistThenReturnTrue() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final ModelTask task = new ModelTask(1, "name");
        cash.add(task.getId(), task);
        final boolean result = cash.delete(task.getId());
        Assert.assertThat(result, is(true));
    }

    @Test
    public void whenDeleteTaskWhichNotExistThenException() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final boolean result = cash.delete(1);
        Assert.assertThat(result, is(false));
    }
}