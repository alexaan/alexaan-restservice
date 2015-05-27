package alexaan.dao;

import alexaan.resourcesupport.CustomerResourceSupport;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for communication between java objects and DB entries
 * Created by Alexander on 24.05.2015.
 * @see alexaan.dao.CustomerDAO Interface Class
 */
public class JdbcCustomerDAO implements CustomerDAO {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Insert customer in DB
     * @param crs Customer to be inserted
     * @throws java.sql.SQLException
     */
    public void insert(CustomerResourceSupport crs){
        String sql = "INSERT INTO CUSTOMER " +
                "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
        Connection conn = null;


        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, crs.getCId());
            ps.setString(2, crs.getName());
            ps.setInt(3, crs.getAge());
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


    /**
     * Looks up a customer in the DB
     * @param custId Corresponds with the CUST_ID DB column of the customer to find
     * @throws java.sql.SQLException
     * @return CustomerResourceSupport representing the returned customer
     */
    public CustomerResourceSupport findByCustomerId(int custId){
        String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, custId);
            CustomerResourceSupport crs = null;
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                crs = new CustomerResourceSupport(
                        rs.getInt("CUST_ID"),
                        rs.getString("NAME"),
                        rs.getInt("Age")
                );
            }
            rs.close();
            ps.close();
            return crs;
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

    /**
     * Looks up all Customer in the DB
     * @throws java.sql.SQLException
     * @return List of type CustomerResourceSupport representing the returned Customers
     */
    @Override
    public List<CustomerResourceSupport> findAllCustomers() {
        String sql = "SELECT * FROM CUSTOMER";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setInt(1, custId);
            CustomerResourceSupport customer = null;
            CustomerResourceSupport[] cl = new CustomerResourceSupport[]{};
            List<CustomerResourceSupport> clm = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                customer = new CustomerResourceSupport(
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

    /**
     * Looks up Customer(s) in the DB based on the name parameter
     * @param name Corresponds with DB table Customer column NAME
     * @throws java.sql.SQLException
     * @return List of type CustomerResourceSupport representing the returned Customers
     */
    @Override
    public List<CustomerResourceSupport> findAllCustomersWithName(String name) {
        String sql = "SELECT * FROM CUSTOMER WHERE NAME LIKE '%"+name+"%'";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setString(1, "'%al%'");
            CustomerResourceSupport customer = null;
            ResultSet rs = ps.executeQuery();
            List<CustomerResourceSupport> clm = new ArrayList<CustomerResourceSupport>();
            while(rs.next()) {
                customer = new CustomerResourceSupport(
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

    /**
     * Looks up Customer(s) in the DB based on the age parameter
     * @param age Corresponds with DB table Customer column AGE
     * @throws java.sql.SQLException
     * @return List of type CustomerResourceSupport representing the returned Customers
     */
    @Override
    public List<CustomerResourceSupport> findAllCustomersWithAge(int age) {
        String sql = "SELECT * FROM CUSTOMER WHERE AGE = ?";
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, age);
            CustomerResourceSupport customer = null;
            ResultSet rs = ps.executeQuery();
            List<CustomerResourceSupport> clm = new ArrayList<CustomerResourceSupport>();
            while(rs.next()) {
                customer = new CustomerResourceSupport(
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
