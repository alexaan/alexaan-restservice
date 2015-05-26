package alexaan.resourcesupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class PostReturnResourceSupport extends ResourceSupport {

    private final String returnString;

    @JsonCreator
    public PostReturnResourceSupport(@JsonProperty("returnString") String returnString) {
        this.returnString = returnString;
    }

    public String getReturnString() {
        return returnString;
    }
}