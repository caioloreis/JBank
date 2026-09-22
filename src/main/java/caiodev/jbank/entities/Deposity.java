package caiodev.jbank.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_deposity")
public class Deposity {

    @Id
    @Column(name = "deposity_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID deposityId;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Column(name = "deposity_value")
    private BigDecimal depositVallue;

    @Column(name = "deposit_date_time")
    private LocalDateTime depositDateTime;

    @Column(name = "ip_adress")
    private String ipAdress;

    public Deposity() {
    }

    public UUID getDeposityId() {
        return deposityId;
    }

    public void setDeposityId(UUID deposityId) {
        this.deposityId = deposityId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public BigDecimal getDepositVallue() {
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
