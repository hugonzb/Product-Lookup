/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author baihu868
 */
public class CustomerDatabase implements CustomerDAOInterface {
    private String customerUri = DbConnection.getDefaultConnectionUri();

    public CustomerDatabase() {
    }
    
    public CustomerDatabase(String customerUri) {
        this.customerUri = customerUri;
    }
    
    

    @Override
    public void save(Customer customer) {
        String sql="insert into Customer (username, firstName, surName, emailAddress, shippingAddress, passWord) values (?,?,?,?,?,?)";

        try (
            // get connection to database
            Connection dbCon = DbConnection.getConnection(customerUri);

            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);
          ) {
            // copy the data from the product domain object into the SQL parameters
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getSurName());
            stmt.setString(4, customer.getEmailAddress());
            stmt.setString(5, customer.getShippingAddress());
            stmt.setString(6, customer.getPassWord());

            stmt.executeUpdate();  // execute the statement

            } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Customer getCustomer(String user) {
        String sql = "select * from Customer where username = ?";

        try (
            // get connection to database
            Connection connection = DbConnection.getConnection(customerUri);

            // create the statement
            PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
        // set the parameter
        stmt.setString(1, user);

        // execute the query
        ResultSet rs = stmt.executeQuery();

        // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                Integer customerID = rs.getInt("customerID");   
                String username = rs.getString("username");
                String firstName = rs.getString("firstName");
                String surName = rs.getString("surName");
                String passWord = rs.getString("passWord");
                String emailAddress = rs.getString("emailAddress");
                String shippingAddress = rs.getString("shippingAddress");

            return new Customer(customerID, username, firstName, surName, passWord, emailAddress, shippingAddress);

        } else {
            return null;
        }

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Boolean validateCredentials(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
