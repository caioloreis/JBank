package caiodev.jbank.exception;

import org.springframework.http.ProblemDetail;

public abstract class JbankException extends RuntimeException {

    public JbankException(String message) {
        super(message);
    }

    public JbankException(Throwable cause) {
        super(cause);
    }

    public ProblemDetail toProblemDatil(){
        var pd = ProblemDetail.forStatus(500);

        pd.setTitle("Jbank Internal Server Error");
        pd.setDetail("Contact JBank support");

        return pd;
    }
}
