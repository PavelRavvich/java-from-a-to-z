package ru.pravvich.start;

import org.junit.Test;
import ru.pravvich.models.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class StartUITest {

    /**
     * @see StartUI#startUpp()
     */
    @Test
    public void whenParamInThenMoreOneMethodWorkInOneSetApp() {
        String[] args = {"0","NAME","DESCRIPTION","n","0","name","desc", "y"};
        Input input = new StubInput(args);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        assertThat(startUI.getTracker().getPrintArray().length,is(2));
    }

    /**
     * @see StartUI#startUpp()
     * @see BaseAction.AddTask
     */
    @Test
    public void whenAddWorkThenStartUppAddTaskQuit() {
        String[] args = {"0","NAME","DESCRIPTION","y"};
        Input input = new StubInput(args);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        assertNotNull(startUI.getTracker().getItems()[0]);
    }

    /**
     * @see StartUI#startUpp()
     * @see BaseAction.DeleteTask
     */
    @Test
    public void whenThenItemBecomeNull() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("NAME");
        item.setDescription("DESCRIPTION");
        tracker.add(item);
        Integer id  = tracker.getItems()[0].getId();
        Input input = new StubInput(new String[]{"8", id.toString(), "y"});
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.startUpp();
        Item result = startUI.getTracker().getItems()[0];
        assertThat(result,is(nullValue(null)));
    }

    /**
     * @see StartUI#startUpp()
     * @see MenuTracker#select(int)
     */
    @Test
    public void whenKeyNotReservingThenMassageError() {
        String[] answers = {"n","y"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        /**
         * не знаю как тут assertThat применить у меня сообщение об ошибке сразу через System.out выводится
         * @see MenuTracker#select(int) вот тут. Может его тут вообще не надо логирование работает и ок...
         */
    }

    /**
     * @see StartUI#startUpp()
     * @see BaseAction.FindById
     */
    @Test
    public void whenIdInThenFindById() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("NAME");
        item.setDescription("DESC");
        tracker.add(item);
        Integer id = tracker.getItems()[0].getId();
        Input input = new StubInput(new String[]{"6",id.toString(),"y"});
        StartUI startUI =  new StartUI(input);
        startUI.setTracker(tracker);
        startUI.startUpp();
        int check = tracker.getItems()[0].getId();
        int result = startUI.getTracker().getItems()[0].getId();
        assertThat(result, is(check));
    }

    /**
     * @see StartUI#startUpp()
     * @see BaseAction.UpdateTask
     */
    @Test
    public void whenUpdateItemWorkThenOldItemReplaceOnNewItem() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("NAME");
        item.setDescription("DESC");
        tracker.add(item);
        Integer id = tracker.getItems()[0].getId();
        Input input = new StubInput(new String[]{"1", id.toString(), "newName", "y"});
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.startUpp();
        assertThat(startUI.getTracker().getItems()[0].getHeader(),is("newName"));
    }

    /**
     * @see StartUI#startUpp()
     * @see BaseAction.EditDescription
     */
    @Test
    public void whenIdAndDescriptionInThenAddDescriptionInItem() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("NAME");
        item.setDescription("DESC");
        tracker.add(item);
        Integer id = tracker.getItems()[0].getId();
        Input input = new StubInput(new String[]{"2", id.toString(), "my description", "y"});
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.startUpp();
        assertThat(startUI.getTracker().getItems()[0].getDescription(),is("my description"));
    }

    /**
     * @see StartUI#startUpp()
     * @see BaseAction.ViewAllTasks
     */
    @Test
    public void whenThenAllTasksWithoutFilterOutPrint() {
        Tracker tracker = new Tracker();
        Item itemFirst = new Item();
        itemFirst.setHeader("NAME_01");
        itemFirst.setDescription("DESCRIPTION_01");
        tracker.add(itemFirst);
        Item itemSecond = new Item();
        itemSecond.setHeader("NAME_02");
        itemSecond.setDescription("DESCRIPTION_02");
        tracker.add(itemSecond);
        String[] answers = {"9","y"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.startUpp();
        assertThat(startUI.getTracker().getItems()[0].getHeader(),is("NAME_01"));
        assertThat(startUI.getTracker().getItems()[1].getHeader(),is("NAME_02"));
    }

    /**
     * @see StartUI#startUpp()
     * @see BaseAction#initActions() там класс ананимный прямо в методе объявлен
     */
    @Test
    public void whenThenAllTasksWithFilterOutPrint() {
        Tracker tracker = new Tracker();
        Item itemFirst = new Item();
        itemFirst.setHeader("NAME_01");
        itemFirst.setDescription("DESCRIPTION_01");
        tracker.add(itemFirst);
        Item itemSecond = new Item();
        itemSecond.setHeader("NAME_02");
        itemSecond.setDescription("DESCRIPTION_02");
        tracker.add(itemSecond);
        String[] answers = {"10","y"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.startUpp();
        assertThat(startUI.getTracker().getArrPrintFilter()[0].getHeader(),is("NAME_02"));
        assertThat(startUI.getTracker().getArrPrintFilter()[1].getHeader(),is("NAME_01"));
    }

    /**
     * @see StartUI#startUpp()
     * @see BaseAction.FindByHeader
     */
    @Test
    public void whenHeaderInThenFindByHeader() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("NAME");
        item.setDescription("DESC");
        tracker.add(item);
        Input input = new StubInput(new String[]{"7","NAME","y"});
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        startUI.startUpp();
        assertThat(startUI.getTracker().getItems()[0].getHeader(), is("NAME"));
        assertThat(startUI.getTracker().getItems()[0].getDescription(), is("DESC"));
    }

    /**
     * @see StartUI#startUpp()
     * @see BaseAction.AddCommit
     */
    @Test
    public void whenIdAndStringInThenItemWithIdAddCommit() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("NAME");
        item.setDescription("DESC");
        tracker.add(item);
        Integer id = tracker.getItems()[0].getId();
        Input input = new StubInput(new String[]{"3",id.toString(), "COMMIT","y"});
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        // check
        startUI.startUpp();
        String result = startUI.getTracker().getItems()[0].getCommits().get(0);
        assertThat(result, is("COMMIT"));
    }

    /**
     * @see StartUI#startUpp()
     * @see BaseAction.DeleteCommit
     */
    @Test
    public void whenOldCommitAndNewCommitInThenOldCommitReplaceOnNewCommit() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("NAME");
        item.setDescription("DESC");
        tracker.add(item);
        tracker.addCommit(tracker.getItems()[0],"COMMIT");
        Input input = new StubInput(new String[]{"4","COMMIT", "NEW_COMMIT","y"});
        StartUI startUI = new StartUI(input);
        startUI.setTracker(tracker);
        // check replace
        startUI.startUpp();
        assertThat(startUI.getTracker().getItems()[0].getCommits().get(0),is("NEW_COMMIT"));
    }
}
