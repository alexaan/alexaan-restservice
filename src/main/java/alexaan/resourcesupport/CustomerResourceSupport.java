package alexaan.resourcesupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class CustomerResourceSupport extends ResourceSupport {

    private final int id;
    private final String name;
    private final int age;

    @JsonCreator
    public CustomerResourceSupport(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("age") int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getCId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}