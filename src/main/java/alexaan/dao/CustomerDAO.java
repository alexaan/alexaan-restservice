package alexaan.dao;

import alexaan.resourcesupport.CustomerResourceSupport;

import java.util.List;

/**
 * Created by Alexander on 24.05.2015.
 */
public interface CustomerDAO {

    public void insert(CustomerResourceSupport crs);
    public CustomerResourceSupport findByCustomerId(int custId);
    public List<CustomerResourceSupport> findAllCustomers();
    public List<CustomerResourceSupport> findAllCustomersWithName(String name);
    public List<CustomerResourceSupport> findAllCustomersWithAge(int age);
}
