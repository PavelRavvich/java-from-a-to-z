package nonBlockingCash.user;

import nonBlockingCash.cash.Cash;
import nonBlockingCash.cash.DataIsLostException;
import nonBlockingCash.cash.NonBlockingCash;
import nonBlockingCash.taskModel.ModelTask;
import nonBlockingCash.taskModel.Task;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class UserTest {
    @Test
    public void whenUserAddNewTaskThenReturnTrue() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user = new User<>(cash);
        final Task task = new ModelTask(1, "test");
        final boolean result = user.add(task.getId(), task);
        Assert.assertThat(result, is(true));
    }

    @Test
    public void whenUserAddSameTaskSecondTimeThenReturnFalse() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user = new User<>(cash);
        final Task task = new ModelTask(1, "test");
        user.add(task.getId(), task);
        final boolean result = user.add(task.getId(), task);
        Assert.assertThat(result, is(false));
    }

    @Test
    public void whenUserGetExistTaskThenReturnTrue() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user = new User<>(cash);
        final Task task = new ModelTask(1, "test");
        user.add(task.getId(), task);
        final Task result = user.get(task.getId());
        Assert.assertThat(result, is(task));
    }

    @Test
    public void whenUserGetNotExistTaskInLocalCashNotGlobalCashThenReturnNull() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user = new User<>(cash);
        final Task result = user.get(1);
        Assert.assertNull(result);
    }

    @Test(expected = DataIsLostException.class)
    public void whenUserGetTaskWhichExistInLocalCashAndNotExistInGlobalCashThenTrowException() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user = new User<>(cash);
        final Task task = new ModelTask(1, "test");
        user.add(task.getId(), task);
        cash.delete(1);
        user.get(task.getId());
    }

    @Test
    public void whenInLocalCashTaskNotExistAndGlobalCashIsExistThenTaskLoadInLocalCashAndGetReturnTask() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user = new User<>(cash);
        final Task task = new ModelTask(1, "test");
        cash.add(1, task);
        final Task result = user.get(1);
        Assert.assertThat(result, is(task));
    }

    @Test(expected = DataIsLostException.class)
    public void whenUserGetTaskAndVersionOfTaskInLocalCashAndGlobalCashDifferentThenTrowException() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user = new User<>(cash);
        final Task task = new ModelTask(1, "test");
        user.add(task.getId(), task);
        cash.delete(task.getId());
        final ModelTask task1 = new ModelTask(1, "test");
        task1.incrementVersion();
        cash.add(task1.getId(), task1);
        user.get(task.getId());
    }

    @Test
    public void whenDeletedTaskWhichExistInBothCashesThenReturnTrue() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user = new User<>(cash);
        final Task task = new ModelTask(1, "test");
        user.add(task.getId(), task);
        final boolean result = user.delete(task.getId());
        Assert.assertThat(result, is(true));
    }

    @Test
    public void whenDeletedTaskWhichNotExistNoGlobalCashNoLocalCashThenReturnFalse() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user = new User<>(cash);
        final boolean result = user.delete(1);
        Assert.assertThat(result, is(false));
    }


    @Test
    public void whenUpdateTaskWhichNotExistThenReturnFalse() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user = new User<>(cash);
        final boolean result = user.update(1, "test");
        Assert.assertThat(result, is(false));
    }

    @Test
    public void whenUpdateTaskWhichExistThenReturnTrue() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user = new User<>(cash);
        final ModelTask task = new ModelTask(1, "test");
        user.add(task.getId(), task);
        final boolean result = user.update(1, "test01");
        Assert.assertThat(result, is(true));
    }

    @Test(expected = DataIsLostException.class)
    public void whenTaskOfFirstUserWillBeUpdateSecondUserAndFirstUserWantUpdateAgainThenException() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user1 = new User<>(cash);
        final User<Integer, Task> user2 = new User<>(cash);
        final ModelTask task = new ModelTask(1, "test");
        user1.add(task.getId(), task);
        user2.update(1, "test01");
        user1.update(1, "test01");
    }

    @Test(expected = DataIsLostException.class)
    public void whenTaskOfFirstUserWillBeUpdateSecondUserAndFirstUserWantGetThenException() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user1 = new User<>(cash);
        final User<Integer, Task> user2 = new User<>(cash);
        final ModelTask task = new ModelTask(1, "test");
        user1.add(task.getId(), task);
        user2.update(1, "test01");
        user1.get(1);
    }

    @Test(expected = DataIsLostException.class)
    public void whenTaskOfFirstUserWillBeDeleteSecondUserAndFirstUserWantUpdateThisTaskThenException() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user1 = new User<>(cash);
        final User<Integer, Task> user2 = new User<>(cash);
        final ModelTask task = new ModelTask(1, "test");
        user1.add(task.getId(), task);
        user2.delete(1);
        user1.update(1, "test01");
    }

    @Test(expected = DataIsLostException.class)
    public void whenTaskOfFirstUserWillBeDeleteSecondUserAndFirstUserWantGetThisTaskThenException() {
        final Cash<Integer, Task> cash = new NonBlockingCash();
        final User<Integer, Task> user1 = new User<>(cash);
        final User<Integer, Task> user2 = new User<>(cash);
        final ModelTask task = new ModelTask(1, "test");
        user1.add(task.getId(), task);
        user2.delete(1);
        user1.get(1);
    }
}