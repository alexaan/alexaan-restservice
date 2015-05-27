package alexaan.exception;

/**
 * Created by Alexander on 27.05.2015.
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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
