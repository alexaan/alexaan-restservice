package hello;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping("/heroku")
public class HerokuController {

    private static final String TEMPLATE = "Hello, %s!";
    private static final String NEWTEMPLATE = "Registered customer with id %s: name: %s. age: %s.";

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<CustomerResourceSupport> greeting(
    //public String greeting(
            //@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
            //@RequestParam(value = "age", required = true) int age,
            @RequestParam(value = "id", required = true) int id) {



        //App.postToDB(name, id, age);
        Customer c = App.getFromDB(id);
        Greeting greeting = new Greeting(String.format(NEWTEMPLATE, c.custId, c.name, c.age));
        CustomerResourceSupport crs = new CustomerResourceSupport(c.custId, c.name, c.age);
        //greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
        //System.out.println("name: "+name);
        //System.out.println("id: "+id);
        String returnstring = new String (String.format(NEWTEMPLATE, c.custId, c.name, c.age));
        //return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
        return new ResponseEntity<CustomerResourceSupport>(crs, HttpStatus.OK);
        //return returnstring;
    }

    @RequestMapping("/getall")
    @ResponseBody
    public HttpEntity<List<CustomerResourceSupport>> getAll (){
        List<Customer> clm = App.getAllCustomers();
        List<CustomerResourceSupport> crsl = new ArrayList<CustomerResourceSupport>();;
        for(Customer c : clm){
            CustomerResourceSupport crs = new CustomerResourceSupport(c.custId, c.name, c.age);
            System.out.println("adding this to crsl: "+crs.getName());
            crsl.add(crs);
        }
        return new ResponseEntity<List<CustomerResourceSupport>>(crsl, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity<Greeting> greetings(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "age", required = true) int age)  {

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());

        App.postToDB(name, id, age);
        System.out.println("name: "+name);
        System.out.println("id: "+id);

        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }
}