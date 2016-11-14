package ru.pravvich.start;

/**
 * @since Pavel Ravvich
 * @since 14.11.2016
 */
public class StartUI {
    private Input input;
    private Tracker tracker = new Tracker();

    /**
     * Тут конечно хорошо бы создать еще класс Actions и реализацию
     * всех действий поместить в него а то в абстрактном классе как-то не комельфо это держать...
     * к тому же тут теперь ананимный класс создавать пришлось... а как еще тут разойтись добавив только ОДИН
     * класс BaseAction что-то мне в голову ничего не приходит. Если только в MenuTracker засунуть? Но тогда
     * получится в меню трекер внутренние классы унаследованые от третего BaseAction, И ЭТО УЧИТЫВАЯ
     * что сам MenuTracker уже занят своим делом. В общем мне это показалось ну уж совсем
     * каким-то не правильным. Не знаю почему но что-то не то. Так что в итоге так сделал, хотя этот
     * BaseAction actions = new BaseAction("Welcome to task manager!") ну откровенный костыль.
     */
    BaseAction actions = new BaseAction("Welcome to task manager!") {

        @Override
        public int key() {
            return -1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
        }

        @Override
        public String info() {
            return String.format("%s", getNameAction());
        }
    };

    // only for tests
    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    public Tracker getTracker() {
        return this.tracker;
    }

    public StartUI(Input input) {
        this.input = input;
    }

    public void validateSelect(MenuTracker menuTracker, int key) {
        try {
            menuTracker.select(key);
        } catch (Exception ex) {
            ex = new MenuIndexException("Invalid value. Enter valid value!");
            System.err.println(ex.getMessage());
        }
    }

    public void startUpp() {
        System.out.println(this.actions.info());
        MenuTracker menuTracker = new MenuTracker(this.input,this.tracker);
        this.actions.initActions();
        // для каждого элемента getActions() вызвать menuTracker.addActions(getActions()[0++])
        this.actions.getActions().forEach(menuTracker::addActions);
        menuTracker.showMenu();
        do {
            int key = this.input.askInt("Select action: ");
            validateSelect(menuTracker, key);
            menuTracker.showMenu();
        } while (!("y").equals(this.input.ask("For quit enter (y) : ")));
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
    }
}