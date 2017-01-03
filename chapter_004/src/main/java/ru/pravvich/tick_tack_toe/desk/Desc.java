package ru.pravvich.tick_tack_toe.desk;

import ru.pravvich.tick_tack_toe.*;

import static java.lang.String.format;

/**
 * Determines desc.
 */
public class Desc implements Board {

    /**
     * Information about statement desc for bot.
     */
    private static char[][] infoDesc;


    /**
     * Input for get console in.
     */
    private In input = new Input();

    /**
     * Desc for game.
     */
    private char[][] desc = new char[3][3];

    /**
     * Pointer on desc. It's information for bot about statement desc.
     *
     * @return current statement desc.
     */
    public static char[][] getInfoDesc() {
        return infoDesc;
    }

    /**
     * Use for test class StubInput.
     *
     * @param input emulation console input stream.
     */
    @Override
    public void setInput(In input) {
        this.input = input;
    }

    /**
     * Getter for desc. Init infoDesc.
     *
     * @return desc.
     */
    @Override
    public char[][] getDesc() {
        infoDesc = this.desc;
        return this.desc;
    }

    /**
     * For choice desc size.
     */
    @Override
    public void initDescSize() {
        System.out.println("Хотите использовать стандартный размер поля: y/n");
        String answer = this.input.getStrInput();
        if (answer.equals("y")) {
            System.out.println("Установлен стандартный размер поля: 3/3");
        } else if (answer.equals("n")) {
            this.initNonstandardDesc();
        }
        this.initContentDesc();
    }

    /**
     * Install desc nonstandard size.
     */
    private void initNonstandardDesc() {
        System.out.println("Введите размер сторон:");
        int i = this.input.getNumInput();
        this.desc = new char[i][i];
        System.out.println(format("Установлен размер поля: %s/%s", i, i));
    }

    /**
     * Fill desc empty call. Empty is ' '.
     */
    private void initContentDesc() {
        for (int i = 0; i != this.desc.length; i++) {
            for (int j = 0; j != this.desc.length; j++) {
                this.desc[j][i] = ' ';
            }
        }
    }
}
