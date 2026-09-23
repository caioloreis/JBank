package caiodev.jbank.repository;

import caiodev.jbank.entities.Deposits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepositRepository extends JpaRepository<Deposits, UUID> {
}
