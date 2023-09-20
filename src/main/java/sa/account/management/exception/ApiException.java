package sa.account.management.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiException extends RuntimeException {

    private final HttpStatus status;
    private final String message;


    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "\"status\": \"" + status + "\"," +
                "\"message\": \"" + message + "\"," +
                "}";
    }
}