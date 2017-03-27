package moneyTransfer.user;

import java.math.BigDecimal;

public interface Account extends IdentificationUser {
    BigDecimal getAmount();
    boolean sendMoney(final BigDecimal pay);
    void takeMoney(final BigDecimal payment);
}
