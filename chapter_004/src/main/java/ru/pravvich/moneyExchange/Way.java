package ru.pravvich.moneyExchange;

import java.util.ArrayList;
import java.util.List;

class Way {
    private List<Integer[]> cases = new ArrayList<>();

    void addCase(Integer[] c) {
        cases.add(c);
    }
}

class WaysAll {
    private List<Way> ways = new ArrayList<>();

    public void addWay(Way way) {
        ways.add(way);
    }
}

class ExchangeMoney {
    WaysAll waysAll = new WaysAll();


}
