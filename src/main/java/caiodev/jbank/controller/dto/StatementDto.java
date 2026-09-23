package caiodev.jbank.controller.dto;

import java.util.List;

public record StatementDto(WalletDto wallet,
                           List<StatementsIntemDto> statements,
                           PaginationDto paginationDto) {
}
