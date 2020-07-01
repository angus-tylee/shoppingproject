
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author angustylee
 */
public class CustomerDAODatabase implements CustomerDAO {

    private String database_url = DbConnection.getDefaultConnectionUri();

    public CustomerDAODatabase() {
    }

    public CustomerDAODatabase(String database_url) {
        this.database_url = database_url;
    }

    @Override
    public void save(Customer customer) {

        String sql = "insert into customers (Username, FirstName, Surname, Password, ShippingAddress, EmailAddress) values (?,?,?,?,?,?)";
        try (
                // get connection to database
                Connection connection = DbConnection.getConnection(database_url);
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // copy the data from the student domain object into the SQL parameters
            

            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getSurname());
            stmt.setString(4, customer.getPassword());
            stmt.setString(5, customer.getShippingAddress());
            stmt.setString(6, customer.getEmailAddress());

            stmt.executeUpdate();  // execute the statement
            
            System.out.println("Saved customer: " + customer);
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Customer getCustomer(String user) {

        String sql = "select * from customers where username = ?";

        try (
                // get connection to database
                Connection connection = DbConnection.getConnection(database_url);
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // set the parameter
            stmt.setString(1, user);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                Customer customer = new Customer();
                
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setUsername(rs.getString("username"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setSurname(rs.getString("Surname"));
                customer.setPassword(rs.getString("Password"));
                customer.setEmailAddress(rs.getString("EmailAddress"));
                customer.setShippingAddress(rs.getString("ShippingAddress"));

                return customer;

            } else {
                // no student matches given ID so return null
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public Boolean validateCredentials(String username, String password) {
//        Customer customer = getCustomer(username);
//
//        return customer != null && BCrypt.checkpw(password, customer.getPassword());
        return null;
    }
    

    
    
}
