package ru.pravvich.isp.menu.actions;

/**
 * Determines action 1
 */
public class Action_1 implements Action {

    /**
     * Get key.
     * @return key which association with key in paragraph.
     */
    @Override
    public String getKey() {
        return "1";
    }

    /**
     * Name action.
     * @return name action.
     */
    @Override
    public String getName() {
        return "Action_1";
    }

    /**
     * Run function program which user select.
     */
    @Override
    public void work() {
        System.out.println("I Action_1. I work!");
    }
}
