package ru.pravvich.start;

import ru.pravvich.models.Item;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class StartUITest {

    /**
     * @see StartUI#add()
     * @see StartUI#startApp()
     */
    @Test
    public void whenAddWorkThenStartUppAddTaskQuit() {
        String[] answers = {"n -t","task","q"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.startApp();
        Tracker tracker = startUI.getTracker();
        assertNotNull(tracker.getItems()[0]);
        assertNotNull(tracker.getItems()[0].getId());
        assertThat(tracker.getItems()[0].getHeader(), is("task"));
        assertThat(startUI.getStart(),is(false));
    }

    /**
     * @see StartUI#addCommit()
     */
    @Test
    public void whenIdAndStringInThenItemWithIdAddCommit() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("TaskName");
        tracker.add(item);
        Integer id = tracker.getItems()[0].getId();
        Input input = new StubInput(new String[] {id.toString(), "Commit"});
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.addCommit();
        String result = startUI.getTracker().getItems()[0].getCommits().get(0);
        assertThat(result, is("Commit"));
    }

    /**
     * @see StartUI#editionCommit()
     */
    @Test
    public void whenOldCommitAndNewCommitInThenOldCommitReplaceOnNewCommit() {
        Item item = new Item();
        item.setHeader("Task");
        StartUI startUI = new StartUI(new StubInput(new String[] {"COMMIT", "REPLACE_COMMIT"}));
        Tracker tracker = startUI.getTracker();
        tracker.add(item);
        tracker.addCommit(item, "COMMIT");
        // check replace commit
        startUI.editionCommit();
        assertThat(startUI.getTracker().getItems()[0].getCommits().get(0),is("REPLACE_COMMIT"));
    }

    /**
     * @see StartUI#viewAllTasks() without filter
     */
    @Test
    public void whenThenAllTasksWithoutFilterOutPrint() {
        Tracker tracker = new Tracker();
        Item taskFirst = new Item();
        taskFirst.setHeader("Task_01");
        tracker.add(taskFirst);
        Item taskSecond = new Item();
        taskSecond.setHeader("Task_02");
        tracker.add(taskSecond);
        String[] answers = {"view -a"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.viewAllTasks();
        int resultIdTaskFirst = startUI.getTracker().getPrintArray()[0].getId();
        assertThat(resultIdTaskFirst,is(startUI.getTracker().getItems()[0].getId()));
        int resultIdTaskSecond = startUI.getTracker().getPrintArray()[1].getId();
        assertThat(resultIdTaskSecond,is(startUI.getTracker().getItems()[1].getId()));
    }

    /**
     * @see StartUI#viewAllTasks() with filter
     */
    @Test
    public void whenThenAllTasksWithFilterReversOutPrint() {
        // if user want filter
        Tracker tracker = new Tracker();
        Item taskFirst = new Item();
        taskFirst.setHeader("Task_01");
        tracker.add(taskFirst);
        Item taskSecond = new Item();
        taskSecond.setHeader("Task_02");
        tracker.add(taskSecond);
        String[] answers = {"view -f"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.viewAllTasks();
        int resultIdTaskFirst = startUI.getTracker().getArrPrintFilter()[0].getId();
        assertThat(resultIdTaskFirst,is(startUI.getTracker().getItems()[1].getId()));
        int resultIdTaskSecond = startUI.getTracker().getArrPrintFilter()[1].getId();
        assertThat(resultIdTaskSecond,is(startUI.getTracker().getItems()[0].getId()));
    }

    /**
     * @see StartUI#updateItem() replace task on new
     */
    @Test
    public void whenUpdateItemWorkThenOldItemReplaceOnNewItem() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("Task_01");
        tracker.add(item);
        Integer id = tracker.getItems()[0].getId();
        String[] answer = {"UpdateTask",id.toString()};
        Input input = new StubInput(answer);
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.updateItem();
        assertThat(startUI.getTracker().getItems()[0].getHeader(),is("UpdateTask"));
    }

    /**
     * @see StartUI#addDescription()
     */
    @Test
    public void whenIdAndDescriptionInThenAddDescriptionInItem() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("Task");
        tracker.add(item);
        Integer id = tracker.getItems()[0].getId();
        Input input = new StubInput(new String[] {id.toString(),"NewDescription"});
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.addDescription();
        assertThat(startUI.getTracker().getItems()[0].getDescription(),is("NewDescription"));
    }

    /**
     * @see StartUI#findByHeader()
     */
    @Test
    public void whenHeaderInThenFindByHeader() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("TaskName");
        tracker.add(item);
        Input input = new StubInput(new String[] {"TaskName"});
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.findByHeader();
        int id = tracker.getItems()[0].getId();
        int result = startUI.getTracker().getItems()[0].getId();
        assertThat(result, is(id));
    }

    /**
     * @see StartUI#findById()
     */
    @Test
    public void whenIdInThenItemFindById() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("Task");
        tracker.add(item);
        Integer id = tracker.getItems()[0].getId();
        Input input = new StubInput(new String[] {id.toString()});
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.findById();
        Integer result = startUI.getTracker().getItems()[0].getId();
        assertThat(result,is(id));
    }

    /**
     * @see StartUI#deleteTask()
     */
    @Test
    public void whenThenItemBecomeNull() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("TaskName");
        tracker.add(item);
        Input input = new StubInput(new String[] {"TaskName"});
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        assertNotNull(startUI.getTracker().getItems()[0]);
        // delete
        startUI.deleteTask();
        Item result = startUI.getTracker().getItems()[0];
        assertThat(result,is(nullValue(null)));
    }
}
