package caiodev.jbank.repository.dto;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public interface StatementView  {

    String getStatementId();
    String getType();
    BigDecimal getStatementValue();;
    String getWalletReceiver();
    String getWalletSender();
    LocalDateTime getStatementDateTime();

}
