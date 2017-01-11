package ru.pravvich.isp.menu;

import ru.pravvich.isp.menu.actions.*;
import ru.pravvich.isp.menu.paragraphs.*;
import ru.pravvich.isp.menu.paragraphs.Paragraph;

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
    private ArrayList<ParagraphInterface> paragraphs = new ArrayList<>();

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
        this.paragraphs.add(new Paragraph("1", "Paragraph 1"));
        this.paragraphs.add(new Paragraph("1.1", "\tParagraph 1.1"));
        this.paragraphs.add(new Paragraph("1.1.1", "\t\tParagraph 1.1.1"));
        this.paragraphs.add(new Paragraph("2", "Paragraph 2"));
        this.paragraphs.add(new Paragraph("2.1", "\tParagraph 2.1"));
        this.paragraphs.add(new Paragraph("3", "Paragraph 3"));
    }

    /**
     * Default constructor.
     */
    StartMenu() {
        this.initActions();
        this.initParagraphs();
    }

    /**
     * Print menu.
     * @param paragraphs all paragraphs.
     */
    @Override
    public void showMenu(ArrayList<ParagraphInterface> paragraphs) {
        for (ParagraphInterface paragraph : paragraphs) {
            System.out.println(paragraph.getName());
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
