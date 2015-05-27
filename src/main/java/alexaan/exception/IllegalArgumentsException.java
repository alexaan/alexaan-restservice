package alexaan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Represents the IllegalArgumentsException exception type that can be thrown from application controllers
 * Created by Alexander on 27.05.2015.
 * @see alexaan.controller.GlobalControllerExceptionHandler Controller responsible for throwing exceptions
 */

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class IllegalArgumentsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String throwMessage;

    public IllegalArgumentsException(String throwMessage){
        this.throwMessage = throwMessage;
    }

    public String getThrowMessage(){
        return throwMessage;
    }



}
