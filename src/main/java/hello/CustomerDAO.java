package hello;

import java.util.List;

/**
 * Created by Alexander on 24.05.2015.
 */
public interface CustomerDAO {

    public void insert(Customer customer);
    public Customer findByCustomerId(int custId);
    public List<Customer> findAllCustomers();
}
