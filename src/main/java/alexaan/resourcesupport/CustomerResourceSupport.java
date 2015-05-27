package alexaan.resourcesupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Class used for representing Customer objects for communication purposes between client and DB
 * @see alexaan.controller.CustomerController Controller responsible for handling Customer logic
 */
public class CustomerResourceSupport extends ResourceSupport {

    private final int id;
    private final String name;
    private final int age;

    /**
     * Class constructor
     * @param name Corresponds with DB table Customer column NAME
     * @param id Corresponds with DB table Customer column CUST_ID
     * @param age Corresponds with DB table Customer column AGE
     */
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