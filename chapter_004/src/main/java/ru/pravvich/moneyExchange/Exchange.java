package ru.pravvich.moneyExchange;

import java.util.*;

/**
 * Exchange big money on less coins.
 */
public class Exchange implements Exchanging {
    /**
     * Money which should be exchanging.
     */
    private final int sumForExchange;
    /**
     * Nominal coins.
     */
    private final int[] denominationСoins;

    /**
     * Default constructor.
     *
     * @param sumForExchange    money which should be exchanging.
     * @param denominationСoins array with nominal coins.
     */
    public Exchange(final int sumForExchange, final int... denominationСoins) {
        this.denominationСoins = denominationСoins;
        this.sumForExchange = sumForExchange;
    }

    /**
     * Exchange big money on less coins.
     *
     * @return map with coins. Nominal is key, amount coin is value.
     */
    @Override
    public Map<Integer, Integer> exchange() {
        Map<Integer, Integer> result = new HashMap<>();
        int[] coins = sort();
        int residue = sumForExchange;

        for (int i = 0; i != coins.length; i++) {
            ExchangeByValue exchangeByValue = new ExchangeByValue(
                    residue, coins[i]);

            // how much coins content current residue for denominationСoins[i].
            int amountCoin = exchangeByValue.exchange();
            result.put(coins[i], amountCoin);
            // update residue.
            residue = exchangeByValue.getResidue();
        }
        return result;
    }

    /**
     * Sorting array with coins nominal.
     *
     * @return arr sort by descending.
     */
    private int[] sort() {
        int[] result = denominationСoins;
        for (int i = result.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (result[j] < result[j + 1]) {
                    int temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }
        return result;
    }

    /**
     * Calculate residue and amount coins.
     */
    private class ExchangeByValue {
        /**
         * Residue for division on coins.
         */
        private final int denomination;
        /**
         * Nominal coin.
         */
        private final int divider;

        /**
         * Default constructor.
         *
         * @param denomination Residue for division on coins.
         * @param divider      Nominal coin.
         */
        private ExchangeByValue(final int denomination, final int divider) {
            this.denomination = denomination;
            this.divider = divider;
        }

        /**
         * @return amount coins which may by get for this.denomination.
         */
        private int exchange() {
            return denomination / divider;
        }

        /**
         * @return residue for future exchange.
         */
        private int getResidue() {
            return denomination % divider;
        }
    }
}

