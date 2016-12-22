package ru.pravvich.isp.menu.actions;

/**
 * Determines action 1.1.1
 */
public class Action_1_1_1 implements Action {

    /**
     * Run function program which user select.
     */
    @Override
    public void work() {
        System.out.println("I Action_1_1_1. I work!");
    }

    /**
     * Get key.
     * @return key which association with key in paragraph.
     */
    @Override
    public String getKey() {
        return "1.1.1";
    }

    /**
     * Name action.
     * @return name action.
     */
    @Override
    public String getName() {
        return "Action_1_1_1";
    }
}
