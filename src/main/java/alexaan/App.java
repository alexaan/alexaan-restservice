package alexaan;

import alexaan.dao.CustomerDAO;
import alexaan.resourcesupport.CustomerResourceSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

/**
 * Main class for the Spring alexaan-restservice application. Also holds the application context and
 * serves connections to DAO classes for DB communication purposes
 */
@SpringBootApplication
public class App
{
    private final static ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    /**
     * Connects to a DAO to be used for DB communication when posting a customer
     * @param name Corresponds with DB table Customer column NAME
     * @param id Corresponds with DB table Customer column CUST_ID
     * @param age Corresponds with DB table Customer column AGE
     * @see alexaan.controller.CustomerController Controller responsible for calling this function
     */
    public static void postNewCustomerToDB(String name, int id, int age){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        CustomerResourceSupport crs = new CustomerResourceSupport(id, name, age);
        customerDAO.insert(crs);

    }

    /**
     * Connects to a DAO to be used for DB communication when retrieving a customer based on id
     * @param id Corresponds with DB table Customer column CUST_ID
     * @see alexaan.controller.CustomerController Controller responsible for calling this function
     * @return CustomerResourceSupport representing the found customer
     */
    public static CustomerResourceSupport getCustomerWithId(int id){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findByCustomerId(id);
    }

    /**
     * Connects to a DAO to be used for DB communication when retrieving all registered customers
     * @see alexaan.controller.CustomerController Controller responsible for calling this function
     * @return List of CustomerResourceSupport representing the found customer(s)
     */
    public static List<CustomerResourceSupport> getAllCustomers(){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findAllCustomers();
    }

    /**
     * Connects to a DAO to be used for DB communication when retreiving customer(s) based on part of their name
     * @param name Corresponds with DB table Customer column NAME
     * @see alexaan.controller.CustomerController Controller responsible for calling this function
     * @return List of CustomerResourceSupport representing the found customer(s)
     */
    public static List<CustomerResourceSupport> getAllCustomersWithName(String name){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findAllCustomersWithName(name);
    }


    /**
     * Connects to a DAO to be used for DB communication when retreiving customer(s) based on their age
     * @param age Corresponds with DB table Customer column AGE
     * @see alexaan.controller.CustomerController Controller responsible for calling this function
     * @return List of CustomerResourceSupport representing the found customer(s)
     */
    public static List<CustomerResourceSupport> getAllCustomersWithAge(int age){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findAllCustomersWithAge(age);
    }
}

