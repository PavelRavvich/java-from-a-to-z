package ru.pravvich.start;

import ru.pravvich.models.*;

/**
 * @since Pavel Ravvich
 * @since 09.11.2016
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] userAction = new UserAction[11];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillAction() {
        this.userAction[0] = new AddItem();
        this.userAction[1] = new UpdateItem();
        this.userAction[2] = new EditDescription();
        this.userAction[3] = new AddCommit();
        this.userAction[4] = new EditCommit();
        this.userAction[5] = new DeleteCommit();
        this.userAction[6] = new FindById();
        this.userAction[7] = new FindByHeader();
        this.userAction[8] = new DeleteTask();
        this.userAction[9] = new ShowAll();
        this.userAction[10] = new ShowReplaceFilter();
    }

    public void select(int key) throws IndexOutOfBoundsException {
            this.userAction[key].execute(this.input, this.tracker);
    }

    public void showMenu() {
        for (UserAction action : this.userAction) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem implements UserAction {

        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String header = input.ask("Please enter the task name");
            String description = input.ask("Please enter description");
            tracker.add(new Task(header, description));
            System.out.println(tracker.getMessage());
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(),"Add new task");
        }
    }

    private class UpdateItem implements UserAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            int id = input.askInt("Please enter ID the task for replace: ");
            String header = input.ask("Please enter the task new name:");
            Item item = new Item();
            int oldId = tracker.findById(id).getId();
            item.setId(oldId);
            item.setHeader(header);
            tracker.updateItem(item);
            System.out.println(tracker.getMessage());
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(),"Update name task");
        }
    }

    private class EditDescription implements UserAction {

        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            int oldId = input.askInt("Please enter ID the task for replace description: ");
            String description = input.ask("Please enter new description:");
            int id = tracker.findById(oldId).getId();
            tracker.addDescription(id, description);
            System.out.println(tracker.getMessage());
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(),"Edit description.");
        }
    }

    private class AddCommit implements UserAction {

        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            int oldId = input.askInt("Please enter ID the task for add commit: ");
            String commit = input.ask("Please enter you commit:");
            Item item = tracker.findById(oldId);
            tracker.addCommit(item, commit);
            System.out.println(tracker.getMessage());
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(),"Add commit.");
        }
    }

    private class EditCommit implements UserAction {

        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String oldCommit = input.ask("Please enter you commit for replace:");
            String newCommit = input.ask("Please enter you new commit:");
            tracker.editionCommit(oldCommit, newCommit);
            System.out.println(tracker.getMessage());
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(),"Replase commit.");
        }
    }

    public class DeleteCommit implements UserAction {

        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String commitForDelete = input.ask("Please enter you commit for replace:");
            tracker.deleteCommit(commitForDelete);
            System.out.println(tracker.getMessage());
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(),"Delete commit.");
        }
    }

    public class FindById implements UserAction {

        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            int id = input.askInt("Enter ID task for search:");
            Item item = tracker.findById(id);
            System.out.println(tracker.getMessage());
            // show commits list
            for (int i = 0; i != item.getCommits().size(); i++) {
                if (item.getCommits().get(i) != null) {
                    System.out.println(String.format("%s %s %s %s",
                            "Commit №", (i + 1), ": ", item.getCommits().get(i)));
                }
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(),"Find by ID.");
        }
    }

    public class FindByHeader implements UserAction {

        @Override
        public int key() {
            return 7;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String header = input.ask("Enter header for search:");
            tracker.findByHeader(header);
            System.out.println(tracker.getMessage());
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(),"Find by header.");
        }
    }

    public class DeleteTask implements UserAction {

        @Override
        public int key() {
            return 8;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            int id = input.askInt("Enter ID task for delete:");
            Item item = tracker.findById(id);
            tracker.delete(item);
            System.out.println(tracker.getMessage());
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(),"Delete task.");
        }
    }

    private class ShowAll implements UserAction {

        @Override
        public int key() {
            return 9;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] items = tracker.getPrintArray();
            System.out.println(tracker.getMessage());
            for (Item item : items) {
                System.out.println(String.format("%s \n%s %s",
                        item.getHeader(), "ID : ", item.getId()));
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(),"Show all.");
        }
    }

    private class ShowReplaceFilter implements UserAction {

        @Override
        public int key() {
            return 10;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] items = tracker.getArrPrintFilter();
            System.out.println(tracker.getMessage());
            for (Item item : items) {
                System.out.println(String.format("%s \n%s %s",
                        item.getHeader(), "ID : ", item.getId()));
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(),"Show all with filter.");
        }
    }
}