package ru.pravvich.isp.menu;

import org.junit.Test;

import static org.junit.Assert.*;

public class StartMenuTest {
    @Test
    public void whenLengthLeafsNotBalancedThenMethodIsBalancedReturnFalse() {
        StartMenu startMenu = new StartMenu();
        boolean result = startMenu.isBalanced();
        assertFalse(result);
    }
}