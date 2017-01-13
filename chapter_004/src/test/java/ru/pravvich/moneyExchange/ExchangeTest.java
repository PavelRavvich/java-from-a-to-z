package ru.pravvich.moneyExchange;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ExchangeTest {
    /**
     * Test exchange big money on coins nominal of value: 1,5,10.
     */
    @Test
    public void whenMoneyAndCoinNominalInThenMapNominalByAmountOut() {
        final int moneyForChange = 17;
        final int coinOne = 10;
        final int coinTwo = 5;
        final int coinTre = 1;
        Exchanging exchange = new Exchange(
                moneyForChange, coinOne, coinTwo, coinTre);

        Map<Integer, Integer> result = new HashMap<>();
        result.put(1,2);
        result.put(5,1);
        result.put(10,1);
        assertThat(result, is(exchange.exchange()));
    }
}