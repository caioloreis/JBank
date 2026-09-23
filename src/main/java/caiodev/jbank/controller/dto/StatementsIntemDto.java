package caiodev.jbank.controller.dto;

import java.math.BigDecimal;
import java.sql.Statement;
import java.time.LocalDateTime;

public record StatementsIntemDto(String statementId,
                                 String type,
                                 String literal,
                                 BigDecimal value,
                                 LocalDateTime datetime,
                                 StatementOperation operation) {
}
