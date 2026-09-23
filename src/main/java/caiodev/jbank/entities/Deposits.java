package caiodev.jbank.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_deposits")
public class Deposits {

    @Id
    @Column(name = "deposits_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID deposityId;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Column(name = "deposits_value")
    private BigDecimal depositVallue;

    @Column(name = "deposits_date_time")
    private LocalDateTime depositDateTime;

    @Column(name = "ip_adress")
    private String ipAdress;

    public Deposits() {
    }

    public UUID getDepositsId() {
        return deposityId;
    }

    public void setDepositsId(UUID deposityId) {
        this.deposityId = deposityId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public BigDecimal getDepositVallue(@NotNull @DecimalMin("10.00") BigDecimal value) {
        return depositVallue;
    }

    public void setDepositVallue(BigDecimal depositVallue) {
        this.depositVallue = depositVallue;
    }

    public LocalDateTime getDepositDateTime() {
        return depositDateTime;
    }

    public void setDepositDateTime(LocalDateTime depositDateTime) {
        this.depositDateTime = depositDateTime;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }
}
