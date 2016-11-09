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
    }

    /**
     * @see StartUI#editionCommit()
     */
    @Test
    public void whenOldCommitAndNewCommitInThenOldCommitReplaceOnNewCommit() {
        String[] answers = {"task"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.add();
        Integer id = startUI.getTracker().getItems()[0].getId();
        startUI.setInput(new StubInput(new String[] {id.toString(), "NewTASK"}));
        startUI.addCommit();
        // check replace
        startUI.setInput(new StubInput(new String[] {"NewTASK", "REPLACE"}));
        startUI.editionCommit();
    }

    /**
     * @see StartUI#addCommit()
     */
    @Test
    public void whenIdAndStringInThenItemWithIdAddCommit() {
        String[] answers = {"task"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.add();
        Integer id = startUI.getTracker().getItems()[0].getId();
        startUI.setInput(new StubInput(new String[] {id.toString(), "NewTASK"}));
        startUI.addCommit();
        String result = startUI.getTracker().getItems()[0].getCommits().get(0);
        assertThat(result, is("NewTASK"));
    }

    /**
     * @see StartUI#viewAllTasks() without filter
     */
    @Test
    public void whenThenAllTasksWithoutFilterOutPrint() {
        String[] answers = {"task"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.add();
        startUI.setInput(new StubInput(new String[] {"view -a"}));
        startUI.viewAllTasks();
    }

    /**
     * @see StartUI#viewAllTasks() with filter
     */
    @Test
    public void whenThenAllTasksWithFilterReversOutPrint() {
        // if user want filter
        String[] answers = {"task"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.add();
        startUI.setInput(new StubInput(new String[] {"view -f"}));
        startUI.viewAllTasks();
    }

    /**
     * @see StartUI#updateItem() replace task on new
     */
    @Test
    public void whenUpdateItemWorkThenOldItemReplaceOnNewItem() {
        String[] answers = {"task"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.add();
        Integer id = startUI.getTracker().getItems()[0].getId();
        startUI.setInput(new StubInput(new String[] {"new Name", id.toString()}));
        startUI.updateItem();
        assertThat(startUI.getTracker().getItems()[0].getHeader(),is("new Name"));
    }

    /**
     * @see StartUI#addDescription()
     */
    @Test
    public void whenIdAndDescriptionInThenAddDescriptionInItem() {
        String[] answers = {"task"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.add();
        Integer id = startUI.getTracker().getItems()[0].getId();
        input = new StubInput(new String[] {id.toString(), "my description"});
        startUI.setInput(input);
        startUI.addDescription();
        assertThat(startUI.getTracker().getItems()[0].getDescription(),is("my description"));
    }

    /**
     * @see StartUI#findByHeader()
     */
    @Test
    public void whenHeaderInThenFindByHeader() {
        String[] answers = {"task"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.add();
        startUI.setInput(new StubInput(new String[] {"task"}));
        startUI.findByHeader();
        assertThat(startUI.getTracker().getItems()[0].getHeader(), is("task"));
    }

    /**
     * @see StartUI#findById()
     */
    @Test
    public void whenIdInThenItemFindById() {
        String[] answers = {"task"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.add();
        Integer id = startUI.getTracker().getItems()[0].getId();
        startUI.setInput(new StubInput(new String[] {id.toString(),"task"}));
        startUI.findById();
        String result = startUI.getTracker().getItems()[0].getHeader();
        assertThat(result, is("task"));
    }


    /**
     * @see StartUI#deleteTask()
     */
    @Test
    public void whenThenItemBecomeNull() {
        String[] answers = {"task"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.add();
        // delete
        startUI.setInput(new StubInput(new String[] {"task"}));
        startUI.deleteTask();
        Item result = startUI.getTracker().getItems()[0];
        assertThat(result,is(nullValue(null)));
    }
}
