package ru.pravvich.isp.menu.actions;

/**
 * Determines action 2
 */
public class Action_2 implements Action {

    /**
     * Get key.
     * @return key which association with key in paragraph.
     */
    @Override
    public String getKey() {
        return "2";
    }

    /**
     * Name action.
     * @return name action.
     */
    @Override
    public String getName() {
        return "Action_2";
    }

    /**
     * Run function program which user select.
     */
    @Override
    public void work() {
        System.out.println("I Action_2. I work!");
    }
}
