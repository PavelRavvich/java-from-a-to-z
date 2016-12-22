package ru.pravvich.isp.menu;

import ru.pravvich.isp.menu.actions.*;
import ru.pravvich.isp.menu.paragraphs.*;
import ru.pravvich.isp.menu.paragraphs.paragraph_1.Paragraph_1;
import ru.pravvich.isp.menu.paragraphs.paragraph_2.Paragraph_2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class determines all base elements menu.
 */
class StartMenu implements Menu, MenuLogic {

    /**
     * Contain all actions in program.
     */
    private ArrayList<Action> actions = new ArrayList<>();

    /**
     * Contain paragraphs upper level.
     */
    private ArrayList<Paragraph> paragraphs = new ArrayList<>();

    /**
     * Init all action this program.
     */
    private void initActions() {
        this.actions.add(new Action_1());
        this.actions.add(new Action_2());
        this.actions.add(new Action_1_1());
        this.actions.add(new Action_1_2());
        this.actions.add(new Action_1_1_1());
    }

    /**
     * Init paragraphs upper level.
     */
    private void initParagraphs() {
        this.paragraphs.add(new Paragraph_1());
        this.paragraphs.add(new Paragraph_2());
    }

    /**
     * Default constructor.
     */
    StartMenu() {
        this.initActions();
        this.initParagraphs();
    }

    /**
     * Print menu. Recursive call all sub paragraph in all paragraph.
     * @param paragraphs paragraphs and all contain paragraphs.
     */
    @Override
    public void showMenu(ArrayList<Paragraph> paragraphs) {
        for (Paragraph paragraph : paragraphs) {
            System.out.println(paragraph.getName());
            showMenu(paragraph.getParagraphs());
        }
    }

    /**
     * Call to action by key which get from paragraph.
     * @param command - key. Get from input stream user's.
     */
    @Override
    public void menuWork(String command) {
        for (Action action : this.actions) {
            if (command.equals(action.getKey())) {
                action.work();
                break;
            }
        }
    }

    /**
     * Loop menu. And get input from command line by scanner.
     */
    void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            this.showMenu(this.paragraphs);

            String command = scanner.next();
            while (!"q".equals(command)) {
                this.menuWork(command);
                this.showMenu(this.paragraphs);
                command = scanner.next();
            }
        }
    }
}
