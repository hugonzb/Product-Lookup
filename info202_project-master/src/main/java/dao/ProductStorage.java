/**
 * @author baihu868
 * INFO202 2019
 * ProductStorage
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.util.*;


public class ProductStorage implements DaoInterface {
    static Map<String, Product> productIDList = new HashMap<>(); 
    static Multimap<String,Product> categoryProductList = HashMultimap.create();
    
    @Override
    public void saveProduct(Product product){
        productIDList.put(product.getProductID(), product);
        categoryProductList.put(product.getCategory(), product);
    }
    
    @Override
    public Collection<Product> returnProducts(){
        return productIDList.values();
    }
    
    @Override
    public Collection<Product> returnCategoryProducts(String category){
        boolean doesExist = categoryProductList.containsKey(category);
        if(doesExist == true){
            Collection<Product> products = categoryProductList.get(category);
            return products;
        }
        return null;
    }
    
    @Override
    public Product searchProducts(String productID){
        boolean doesExist = productIDList.containsKey(productID);
        if(doesExist == true){
            return productIDList.get(productID);
        }
        return null;
    }
    
    @Override
    public void removeProduct(Product product){
        productIDList.remove(product.getProductID()); 
        categoryProductList.remove(product.getCategory(), product);
    }
    
    @Override
    public Collection<String> returnCategories(){
        return categoryProductList.keySet();
    }
}
