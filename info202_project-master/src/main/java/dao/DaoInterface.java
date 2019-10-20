
package dao;

import domain.Product;
import java.util.Collection;

public interface DaoInterface {

    void removeProduct(Product product);

    Collection<String> returnCategories();

    Collection<Product> returnCategoryProducts(String category);

    Collection<Product> returnProducts();

    void saveProduct(Product product);

    Product searchProducts(String productID);
    
}
