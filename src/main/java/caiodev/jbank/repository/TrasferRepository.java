package caiodev.jbank.repository;

import caiodev.jbank.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrasferRepository extends JpaRepository<Transfer, UUID> {
}
