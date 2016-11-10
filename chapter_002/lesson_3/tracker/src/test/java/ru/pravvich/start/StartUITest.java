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
     * @see MenuTracker.AddItem
     */
    @Test
    public void whenAddWorkThenStartUppAddTaskQuit() {
        String[] args = {"0","NAME","DESCRIPTION","y"};
        Input input = new StubInput(args);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        boolean result = (startUI.getTracker().getItems()[0] != null);
        assertThat(result,is(true));
    }

    /**
     * @see StartUI#startUpp()
     * @see MenuTracker.DeleteTask
     */
    @Test
    public void whenThenItemBecomeNull() {
        String[] answers = {"0","NAME","DESCRIPTION","y"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        Integer id = startUI.getTracker().getItems()[0].getId();
        String[] answersForDelete = {"8" , id.toString(),"y"};
        Input inputForDelete = new StubInput(answersForDelete);
        startUI.setInput(inputForDelete);
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
    }

    /**
     * @see StartUI#startUpp()
     * @see MenuTracker.FindById
     */
    @Test
    public void whenIdInThenFindById() {
        String[] answers = {"0","NAME","DESCRIPTION","y"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        Integer id = startUI.getTracker().getItems()[0].getId();
        startUI.setInput(new StubInput(new String[] {"6" ,id.toString(),"y"}));
        startUI.startUpp();
        String result = startUI.getTracker().getItems()[0].getHeader();
        assertThat(result, is("NAME"));
    }

    /**
     * @see StartUI#startUpp()
     * @see MenuTracker.UpdateItem
     */
    @Test
    public void whenUpdateItemWorkThenOldItemReplaceOnNewItem() {
        String[] answers = {"0","NAME","DESCRIPTION","y"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        Integer id = startUI.getTracker().getItems()[0].getId();
        startUI.setInput(new StubInput(new String[] {"1", id.toString(), "new Name", "y"}));
        startUI.startUpp();
        assertThat(startUI.getTracker().getItems()[0].getHeader(),is("new Name"));
    }

    /**
     * @see StartUI#startUpp()
     * @see MenuTracker.EditDescription
     */
    @Test
    public void whenIdAndDescriptionInThenAddDescriptionInItem() {
        String[] answers = {"0","NAME","DESCRIPTION","y"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        Integer id = startUI.getTracker().getItems()[0].getId();
        startUI.setInput(new StubInput(new String[] {"2", id.toString(), "my description", "y"}));
        startUI.startUpp();
        assertThat(startUI.getTracker().getItems()[0].getDescription(),is("my description"));
    }

    /**
     * @see StartUI#startUpp()
     * @see MenuTracker.ShowAll
     */
    @Test
    public void whenThenAllTasksWithoutFilterOutPrint() {
        String[] answers = {"0","NAME_01","DESCRIPTION_01","y"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        String[] answers1 = {"0","NAME_02","DESCRIPTION_02","y"};
        Input inputSecond = new StubInput(answers1);
        startUI.setInput(inputSecond);
        startUI.startUpp();
        // check
        startUI.setInput(new StubInput(new String[] {"9","y"}));
        startUI.startUpp();
        assertThat(startUI.getTracker().getItems()[0].getHeader(),is("NAME_01"));
        assertThat(startUI.getTracker().getItems()[1].getHeader(),is("NAME_02"));
    }

    /**
     * @see StartUI#startUpp()
     * @see MenuTracker.ShowReplaceFilter
     */
    @Test
    public void whenThenAllTasksWithFilterOutPrint() {
        String[] answers = {"0","NAME_01","DESCRIPTION_01","y"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        String[] answers1 = {"0","NAME_02","DESCRIPTION_02","y"};
        Input inputSecond = new StubInput(answers1);
        startUI.setInput(inputSecond);
        startUI.startUpp();
        // check
        startUI.setInput(new StubInput(new String[] {"10","y"}));
        startUI.startUpp();
        assertThat(startUI.getTracker().getArrPrintFilter()[0].getHeader(),is("NAME_02"));
        assertThat(startUI.getTracker().getArrPrintFilter()[1].getHeader(),is("NAME_01"));
    }

    /**
     * @see StartUI#startUpp()
     * @see MenuTracker.FindByHeader
     */
    @Test
    public void whenHeaderInThenFindByHeader() {
        String[] answers = {"0","NAME","DESCRIPTION","y"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        startUI.setInput(new StubInput(new String[] {"7","NAME","y"}));
        startUI.startUpp();
        assertThat(startUI.getTracker().getItems()[0].getHeader(), is("NAME"));
        assertThat(startUI.getTracker().getItems()[0].getDescription(), is("DESCRIPTION"));
    }

    /**
     * @see StartUI#startUpp()
     * @see MenuTracker.AddCommit
     */
    @Test
    public void whenIdAndStringInThenItemWithIdAddCommit() {
        String[] answers = {"0","NAME","DESCRIPTION","y"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        // check
        Integer id = startUI.getTracker().getItems()[0].getId();
        startUI.setInput(new StubInput(new String[] {"3",id.toString(), "COMMIT","y"}));
        startUI.startUpp();
        String result = startUI.getTracker().getItems()[0].getCommits().get(0);
        assertThat(result, is("COMMIT"));
    }

    /**
     * @see StartUI#startUpp()
     * @see MenuTracker.DeleteCommit
     */
    @Test
    public void whenOldCommitAndNewCommitInThenOldCommitReplaceOnNewCommit() {
        String[] answers = {"0","NAME","DESCRIPTION","y"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
        // add commit
        Integer id = startUI.getTracker().getItems()[0].getId();
        startUI.setInput(new StubInput(new String[] {"3",id.toString(), "COMMIT","y"}));
        startUI.startUpp();
        // check replace
        startUI.setInput(new StubInput(new String[] {"4","COMMIT", "NEW_COMMIT","y"}));
        startUI.startUpp();
        assertThat(startUI.getTracker().getItems()[0].getCommits().get(0),is("NEW_COMMIT"));
    }
}
