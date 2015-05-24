package hello;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 24.05.2015.
 */
public class JdbcCustomerDAO implements CustomerDAO {

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSource dataSource;

    public void setDatasource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void insert(Customer customer){
        String sql = "INSERT INTO CUSTOMER " +
                "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
        Connection conn = null;


        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer.getCustId());
            ps.setString(2, customer.getName());
            ps.setInt(3, customer.getAge());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){}
            }
        }
    }


    public Customer findByCustomerId(int custId){
        String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, custId);
            Customer customer = null;
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                customer = new Customer(
                        rs.getInt("CUST_ID"),
                        rs.getString("NAME"),
                        rs.getInt("Age")
                );
            }
            rs.close();
            ps.close();
            return customer;
        } catch(SQLException e){ throw new RuntimeException(e);}
        finally {
            if(conn != null){
                try{
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public List<Customer> findAllCustomers() {
        String sql = "SELECT * FROM CUSTOMER";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setInt(1, custId);
            Customer customer = null;
            Customer[] cl = new Customer[]{};
            List<Customer> clm = new ArrayList<Customer>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                customer = new Customer(
                        rs.getInt("CUST_ID"),
                        rs.getString("NAME"),
                        rs.getInt("Age")
                );
                clm.add(customer);
            }
            rs.close();
            ps.close();
            return clm;
        } catch(SQLException e){ throw new RuntimeException(e);}
        finally {
            if(conn != null){
                try{
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
