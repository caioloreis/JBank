package caiodev.jbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

public class DeleteWalletException extends JbankException{

    private final String detail;

    public DeleteWalletException(String detial) {
        super(detial);
        this.detail = detial;
    }

    @Override
    public ProblemDetail toProblemDatil() {
        var pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pd.setTitle("You cannot delete this wallet");
        pd.setDetail(detail);

        return pd;
    }
}
