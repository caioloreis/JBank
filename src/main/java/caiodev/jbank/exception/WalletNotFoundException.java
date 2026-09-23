package caiodev.jbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletNotFoundException extends JbankException{
    private final String detail;

    public WalletNotFoundException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDatil() {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pd.setTitle("Wallet not found");
        pd.setDetail(detail);

        return pd;
    }
}
