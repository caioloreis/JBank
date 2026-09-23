package caiodev.jbank.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import java.math.BigDecimal;

public record TransferMoneyDto(@NotNull UUID sender,
                               @NotNull @DecimalMin("0.01") BigDecimal value,
                               @NotNull UUID receiver) {


}
