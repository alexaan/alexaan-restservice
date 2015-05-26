package alexaan.controller;

import alexaan.*;
import alexaan.resourcesupport.CustomerResourceSupport;
import alexaan.resourcesupport.PostReturnResourceSupport;
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
@RequestMapping("/")
public class CustomerController {

    private static final String TEMPLATE = "Hello, %s!";
    private static final String ADDCUSTOMERTEMPLATE = "Successfully registered customer with id %s, name %s, age %s.";

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<List<CustomerResourceSupport>> getCustomers(
            @RequestParam(value = "id", required = false) Integer id,//, defaultValue="0"
            @RequestParam(value = "age", required = false) Integer age, //defaultValue="0"
            @RequestParam(value = "name", required = false) String name) //, defaultValue = "Placeholder"
    {
        List<CustomerResourceSupport> crsl = new ArrayList<CustomerResourceSupport>();

        if(id!=null)
        {
            CustomerResourceSupport c = App.getCustomerWithId(id);
            CustomerResourceSupport crs = new CustomerResourceSupport(c.getCId(), c.getName(), c.getAge());
            crs.add(linkTo(methodOn(CustomerController.class).getCustomers(id, age, name)).withSelfRel());
            crsl.add(crs);
        }
        if(name!=null){
            List<CustomerResourceSupport> clm = App.getAllCustomersWithName(name);
            for(CustomerResourceSupport c : clm){
                boolean customerAlreadyInResultSet = false;
                CustomerResourceSupport crs = new CustomerResourceSupport(c.getCId(), c.getName(), c.getAge());
                crs.add(linkTo(methodOn(CustomerController.class).getCustomers(id, age, name)).withSelfRel());
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
        if(age!=null) {
            List<CustomerResourceSupport> clm = App.getAllCustomersWithAge(age);
            for(CustomerResourceSupport c : clm){
                CustomerResourceSupport crs = new CustomerResourceSupport(c.getCId(), c.getName(), c.getAge());
                crs.add(linkTo(methodOn(CustomerController.class).getCustomers(id, age, name)).withSelfRel());
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
    }

    @RequestMapping("/getall")
    @ResponseBody
    public HttpEntity<List<CustomerResourceSupport>> getAll (){
        List<CustomerResourceSupport> clm = App.getAllCustomers();
        List<CustomerResourceSupport> crsl = new ArrayList<CustomerResourceSupport>();;
        for(CustomerResourceSupport c : clm){
            CustomerResourceSupport crs = new CustomerResourceSupport(c.getCId(), c.getName(), c.getAge());
            crs.add(linkTo(methodOn(CustomerController.class).getAll()).withSelfRel());
            crsl.add(crs);
        }
        return new ResponseEntity<List<CustomerResourceSupport>>(crsl, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity<PostReturnResourceSupport> postCustomer(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "age", required = true) int age)  {

        PostReturnResourceSupport pr = new PostReturnResourceSupport(String.format(ADDCUSTOMERTEMPLATE, id, name, age));
        pr.add(linkTo(methodOn(CustomerController.class).postCustomer(name, id, age)).withSelfRel());
        App.postNewCustomerToDB(name, id, age);

        return new ResponseEntity<PostReturnResourceSupport>(pr, HttpStatus.OK);
    }
}