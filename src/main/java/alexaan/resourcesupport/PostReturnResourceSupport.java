package alexaan.resourcesupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Class used for representing return messages that shall be sent to the client after having posted to the DB
 * @see alexaan.controller.CustomerController Controller responsible for handling Customer posting logic
 */
public class PostReturnResourceSupport extends ResourceSupport {

    private final String returnString;

    /**
     * Class constructor
     * @param returnString Message string to be returned to the client
     */
    @JsonCreator
    public PostReturnResourceSupport(@JsonProperty("returnString") String returnString) {
        this.returnString = returnString;
    }

    public String getReturnString() {
        return returnString;
    }
}