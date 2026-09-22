package caiodev.jbank.exception;


import org.springframework.http.ProblemDetail;

public class WalletDataAlreadyExistException extends JbankException {

    private final String detail;

    public WalletDataAlreadyExistException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDatil() {
        var pd = ProblemDetail.forStatus(422);

        pd.setTitle("Wallet data already exist");
        pd.setDetail(detail);

        return pd;
    }

}
