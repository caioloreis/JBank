package caiodev.jbank.exception;

import caiodev.jbank.exception.dto.InvalidParamDto;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JbankException.class)
    public ProblemDetail handleJbankException(JbankException e) {
        return e.toProblemDatil();
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
     var invalidParans =   e.getFieldErrors()
                .stream()
                .map(fe -> new InvalidParamDto(fe.getField(), fe.getDefaultMessage()))
                .toList();
        var pd = ProblemDetail.forStatus(400);

        pd.setTitle("Invalid request parameters");
        pd.setDetail("There is invalid fields on the request");
        pd.setProperty("Invalid-params", invalidParans);

        return pd;
    }

}
