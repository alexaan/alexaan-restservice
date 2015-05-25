package alexaan.dao;

import alexaan.Customer;

import java.util.List;

/**
 * Created by Alexander on 24.05.2015.
 */
public interface CustomerDAO {

    public void insert(Customer customer);
    public Customer findByCustomerId(int custId);
    public List<Customer> findAllCustomers();
    public List<Customer> findAllCustomersWithName(String name);
    public List<Customer> findAllCustomersWithAge(int age);
}
