package moneyTransfer.user;

import java.math.BigDecimal;

public class User implements Account {
    private BigDecimal amount;
    private String name;
    private int id;

    public User(final BigDecimal amount) {
        this.amount = amount;
    }

    public User(final BigDecimal amount,
                final String name,
                final int id) {

        this.amount = amount;
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public BigDecimal getAmount() {
        return new BigDecimal(this.amount.toString());
    }

    @Override
    public void takeMoney(final BigDecimal payment) {
        amount = amount.add(payment);
    }

    @Override
    public boolean sendMoney(final BigDecimal pay) {
        if (amount.compareTo(pay) < 0) {
            return false;
        }
        amount = amount.subtract(pay);
        return true;
    }

    @Override
    public String toString() {
        return "Walker{" +
                "amount=" + amount +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
