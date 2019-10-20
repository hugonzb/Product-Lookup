
package web;

import dao.CustomerDAOInterface;
import dao.CustomerDatabase;
import dao.DaoInterface;
import dao.ProductDatabase;
import dao.SaleDAOInterface;
import dao.SaleDatabase;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.json.Gzon;

public class Server extends Jooby {
    
    DaoInterface productDao = new ProductDatabase();
    CustomerDAOInterface customerDao = new CustomerDatabase();
    SaleDAOInterface saleDao = new SaleDatabase();
    
    public Server() {
        use(new Gzon());
        use(new ProductModule(productDao));
        use(new CustomerModule(customerDao));
        use(new SaleModule(saleDao));
        use(new AssetModule());
    }
   
    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Server.");
        Server server = new Server();
        CompletableFuture.runAsync(() -> {
            server.start();
        });
        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop the server.");
        });
        // wait for user to hit the Enter key
        System.in.read();
        System.exit(0);
        }
}
