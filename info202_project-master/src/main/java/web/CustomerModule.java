
package web;

import dao.CustomerDAOInterface;
import dao.CustomerDatabase;
import domain.Customer;
import org.jooby.Jooby;
import org.jooby.Result;
import org.jooby.Status;

public class CustomerModule extends Jooby {
    
    CustomerDAOInterface customerDao = new CustomerDatabase();
    
    public CustomerModule(CustomerDAOInterface customerDao) {
        port(8080);
        get("/api/customers/:username", (req) -> {
            String username = req.param("username").value();
            if(customerDao.getCustomer(username) == null){
                return new Result().status(Status.NOT_FOUND);
            }
            return customerDao.getCustomer(username);
        });
        post("/api/register", (req, rsp) -> {
        Customer customer = req.body().to(Customer.class);
        customerDao.save(customer);
        rsp.status(Status.CREATED);
        });
        
    }
    
}
