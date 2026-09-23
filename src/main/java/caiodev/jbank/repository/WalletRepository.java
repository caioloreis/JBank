package caiodev.jbank.repository;

import caiodev.jbank.entities.Wallet;
import caiodev.jbank.repository.dto.StatementView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    String SQL_STATEMENT = """
            SELECT
                BIN_TO_UUID(transfer_id) AS statement_id,
                'transfer' AS type,
                transfer_value AS statement_value,
                BIN_TO_UUID(wallet_receiver_id) AS wallet_receiver,
                BIN_TO_UUID(wallet_sender_id) AS wallet_sender,
                transfer_date_time AS statement_date_time
            FROM
                tb_transfers
            WHERE wallet_sender_id = UUID_TO_BIN(?1) OR wallet_receiver_id = UUID_TO_BIN(?1)
            
            UNION ALL
            
            SELECT
                BIN_TO_UUID(deposits_id) AS statement_id,
                'deposit' AS type,
                deposits_value AS statement_value,
                BIN_TO_UUID(wallet_id) AS wallet_receiver,
                '' AS wallet_sender,
                deposits_date_time AS statement_date_time
            FROM
                tb_deposits
            WHERE
                wallet_id = UUID_TO_BIN(?1)
            """;

    String SQL_COUNT_STATEMENT = """
            SELECT COUNT(*) FROM
            (
            """ + SQL_STATEMENT + """
            ) AS total
            """;

    Optional<Wallet> findByCpfOrEmail(String cpf, String email);

    @Query(value = SQL_STATEMENT, countQuery = SQL_COUNT_STATEMENT, nativeQuery = true)
    Page<StatementView> findStatements(String walletId, PageRequest pageRequest);
}