package caiodev.jbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class StatementException extends JbankException {
    private String detail;
    public StatementException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDatil() {
        var pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pd.setTitle("Invalid statement scenario");

        return pd;
    }
}
