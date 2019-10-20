
package dao;

import domain.Customer;

public interface CustomerDAOInterface {
    
    void save(Customer customer);
    
    Customer getCustomer(String username);
    
    Boolean validateCredentials(String username, String password);
    
}
