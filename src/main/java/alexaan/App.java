package alexaan;

import alexaan.dao.CustomerDAO;
import alexaan.resourcesupport.CustomerResourceSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

@SpringBootApplication
public class App
{
    private final static ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    public static void postNewCustomerToDB(String name, int id, int age){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        CustomerResourceSupport crs = new CustomerResourceSupport(id, name, age);
        customerDAO.insert(crs);

    }

    public static CustomerResourceSupport getCustomerWithId(int id){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findByCustomerId(id);
    }

    public static List<CustomerResourceSupport> getAllCustomers(){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findAllCustomers();
    }

    public static List<CustomerResourceSupport> getAllCustomersWithName(String name){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findAllCustomersWithName(name);
    }

    public static List<CustomerResourceSupport> getAllCustomersWithAge(int age){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findAllCustomersWithAge(age);
    }
}

