package alexaan.resourcesupport;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class used for representing Error messages
 * Created by Alexander on 27.05.2015.
 * @see alexaan.controller.GlobalControllerExceptionHandler Controller responsible for handling custom application errors
 */
public class ErrorResourceSupport extends ResourceSupport {

    private final String error;

    /**
     * Class constructor
     * @param error Error message of ErrorResourceSupport to be created
     */
    @JsonCreator
    public ErrorResourceSupport(@JsonProperty("error") String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}