package alexaan;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping("/")//heroku
public class HerokuController {

    private static final String TEMPLATE = "Hello, %s!";
    private static final String NEWTEMPLATE = "Registered customer with id %s: name: %s. age: %s.";

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<List<CustomerResourceSupport>> greeting(
            @RequestParam(value = "id", required = false, defaultValue="0") int id,
            @RequestParam(value = "age", required = false, defaultValue="0") int age,
            @RequestParam(value = "name", required = false, defaultValue = "Placeholder") String name)
    {

        List<CustomerResourceSupport> crsl = new ArrayList<CustomerResourceSupport>();
        if(id != 0)
        {
            Customer c = App.getFromDB(id);
            CustomerResourceSupport crs = new CustomerResourceSupport(c.custId, c.name, c.age);
            //crsl = new ArrayList<CustomerResourceSupport>();
            crsl.add(crs);
            //return new ResponseEntity<List<CustomerResourceSupport>>(crsl, HttpStatus.OK);
        }
        if(!name.equalsIgnoreCase("Placeholder")){
            List<Customer> clm = App.getAllCustomersWithName(name);
            //crsl = new ArrayList<CustomerResourceSupport>();
            for(Customer c : clm){
                //System.out.println("sup: "+c.getName());
                boolean customerAlreadyInResultSet = false;
                CustomerResourceSupport crs = new CustomerResourceSupport(c.custId, c.name, c.age);
                    for(CustomerResourceSupport cr : crsl){
                        if(cr.getCId() == crs.getCId()){
                            //System.out.println("name alreadyin "+name);
                            System.out.println(cr.getCId()+" is "+crs.getCId());
                            customerAlreadyInResultSet = true;
                        }else{
                            System.out.println(cr.getCId()+" not "+crs.getCId());
                            }
                    }
                if(customerAlreadyInResultSet == false){
                    System.out.println("adding "+crs.getName());
                    crsl.add(crs);
                }
            }

            //return new ResponseEntity<List<CustomerResourceSupport>>(crsl, HttpStatus.OK);
        }
        if(age != 0) {
            List<Customer> clm = App.getAllCustomersWithAge(age);
            for(Customer c : clm){
                CustomerResourceSupport crs = new CustomerResourceSupport(c.custId, c.name, c.age);
                boolean customerAlreadyInResultSet = false;
                for(CustomerResourceSupport cr : crsl){
                    if(cr.getCId() == crs.getCId()){
                        customerAlreadyInResultSet = true;
                    }
                }
                if(customerAlreadyInResultSet == false){
                    crsl.add(crs);
                }
            }
        }

        Collections.sort(crsl, new Comparator<CustomerResourceSupport>() {
            @Override
            public int compare(CustomerResourceSupport o1, CustomerResourceSupport o2) {
                return Integer.compare(o1.getCId(), o2.getCId());
            }
        });

        return new ResponseEntity<List<CustomerResourceSupport>>(crsl, HttpStatus.OK);

        //Greeting greeting = new Greeting(String.format(NEWTEMPLATE, c.custId, c.name, c.age));
        //greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
        //String returnstring = new String (String.format(NEWTEMPLATE, c.custId, c.name, c.age));
        //return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
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