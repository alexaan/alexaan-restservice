package alexaan.resourcesupport;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResourceSupport extends ResourceSupport {

    private final String error;

    @JsonCreator
    public ErrorResourceSupport(@JsonProperty("error") String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}