package hello;

/**
 * Created by Alexander on 24.05.2015.
 */
public interface CustomerDAO {

    public void insert(Customer customer);
    public Customer findByCustomerId(int custId);
}
