package ru.pravvich.start;

import ru.pravvich.models.Item;
import ru.pravvich.models.Task;

import java.util.ArrayList;

abstract class BaseAction implements UserAction {
    private String nameAction;

    private ArrayList<BaseAction> actions = new ArrayList<>();

    ArrayList<BaseAction> getActions() {
        return this.actions;
    }

    BaseAction(String name) {
        this.nameAction = name;
    }

    String getNameAction() {
        return this.nameAction;
    }

    @Override
    public abstract int key();

    @Override
    public abstract void execute(Input input, Tracker tracker);

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.nameAction);
    }

    void initActions() {
        BaseAction addTask = new AddTask("Add new task");
        this.actions.add(addTask);

        BaseAction updateTask = new UpdateTask("Update task");
        this.actions.add(updateTask);

        BaseAction editDescription = new EditDescription("Edit description.");
        this.actions.add(editDescription);

        BaseAction addCommit = new AddCommit("Add commit.");
        this.actions.add(addCommit);

        BaseAction editCommit = new EditCommit("Edit commit.");
        this.actions.add(editCommit);

        BaseAction deleteCommit = new DeleteCommit("Delete commit.");
        this.actions.add(deleteCommit);

        BaseAction findById = new FindById("Find by ID.");
        this.actions.add(findById);

        BaseAction findByHeader = new FindByHeader("Find by header.");
        this.actions.add(findByHeader);

        BaseAction deleteTask = new DeleteTask("Delete task.");
        this.actions.add(deleteTask);

        BaseAction viewAllTasks = new ViewAllTasks("View all tasks.");
        this.actions.add(viewAllTasks);

        BaseAction viewAllTasksFilter = new BaseAction("Show all tasks with filter.") {
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
        };

        this.actions.add(viewAllTasksFilter);
    }

    private class AddTask extends BaseAction {
        AddTask(String name) {
            super(name);
        }

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
    }

    private class UpdateTask extends BaseAction {
        UpdateTask(String name) {
            super(name);
        }

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
    }

    private class EditDescription extends BaseAction {
        EditDescription(String name) {
            super(name);
        }

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
    }

    private class AddCommit extends BaseAction {
        AddCommit(String name) {
            super(name);
        }

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
    }

    private class EditCommit extends BaseAction {
        EditCommit(String name) {
            super(name);
        }

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
    }

    private class DeleteCommit extends BaseAction {
        DeleteCommit(String name) {
            super(name);
        }

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
    }

    private class FindById extends BaseAction {

        FindById(String name) {
            super(name);
        }

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
    }

    private class FindByHeader extends BaseAction {
        FindByHeader(String name) {
            super(name);
        }

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
    }

    private class DeleteTask extends BaseAction {
        DeleteTask(String name) {
            super(name);
        }

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
    }

    private class ViewAllTasks extends BaseAction {
        ViewAllTasks(String name) {
            super(name);
        }

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
    }
}