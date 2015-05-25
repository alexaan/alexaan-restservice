package alexaan;

import alexaan.dao.CustomerDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class App
{
    public final static ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    public static void  postToDB(String name, int id, int age){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        Customer customer = new Customer(id, name, age);
        customerDAO.insert(customer);

    }

    public static Customer getFromDB(int id){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findByCustomerId(id);
    }

    public static java.util.List<Customer> getAllCustomers(){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findAllCustomers();
    }

    public static java.util.List<Customer> getAllCustomersWithName(String name){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findAllCustomersWithName(name);
    }

    public static java.util.List<Customer> getAllCustomersWithAge(int age){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findAllCustomersWithAge(age);
    }
}

