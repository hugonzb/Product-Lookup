
package web;

import dao.DaoInterface;
import dao.ProductDatabase;
import org.jooby.Jooby;

public class ProductModule extends Jooby {
    
    DaoInterface productDao = new ProductDatabase();

    public ProductModule(DaoInterface productDao) {
        port(8080);
        get("/api/products/", () -> productDao.returnProducts());
        get("/api/categories/", () -> productDao.returnCategories());
        
        get("/api/products/:id", (req) -> {
            String id = req.param("id").value();
            return productDao.searchProducts(id);
        });
        
        get("/api/categories/:category", (req) -> {
            String category = req.param("category").value();
            return productDao.returnCategoryProducts(category);
        });
    }   
}
