/**
 * @author baihu868
 * INFO202 2019
 * ProductDatabase
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductDatabase implements DaoInterface {
    private String productUri = DbConnection.getDefaultConnectionUri();

    public ProductDatabase() {
    }

    public ProductDatabase(String productUri) {
        this.productUri = productUri;
    }

    @Override
    public void removeProduct(Product product) {
        String sql = "delete from Product where productID = ?";
        try (
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(productUri);
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);
        ) {
               stmt.setString(1, product.getProductID()); 
               stmt.executeUpdate(); 

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }     
    }

    @Override
    public Collection<String> returnCategories() {
        String sql = "select distinct category from Product";

        try (
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(productUri);

            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);
        ) {
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            Collection<String> categories = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String Category = rs.getString("category");
        
                // and put it in the collection
                categories.add(Category);
            }

            return categories;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }     
    }

    @Override
    public Collection<Product> returnCategoryProducts(String specifiedcategory) {
        String sql = "select * from Product where category = ?";

        try (
            // get connection to database
            Connection connection = DbConnection.getConnection(productUri);

            // create the statement
            PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
        // set the parameter
        stmt.setString(1, specifiedcategory);
        
        List<Product> products = new ArrayList<>();

        // execute the query
        ResultSet rs = stmt.executeQuery();

        // query only returns a single result, so use 'if' instead of 'while'
            while (rs.next()) {
                String productID = rs.getString("productID");   
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal listPrice = rs.getBigDecimal("listPrice");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityInStock");

            Product p = new Product(productID, name, description, category, listPrice, quantityInStock);
            
            products.add(p);

            } 
            return products;

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public Collection<Product> returnProducts() {
        String sql = "select * from Product order by productID";

        try (
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(productUri);

            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);
        ) {
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            List<Product> products = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String productID = rs.getString("productID");   
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal listPrice = rs.getBigDecimal("listPrice");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityInStock");
        

                // use the data to create a product object
                Product p = new Product(productID, name, description, category, listPrice, quantityInStock);

                // and put it in the collection
                products.add(p);
            }

            return products;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void saveProduct(Product product) {
        String sql="insert into Product (productID, name, description, category, listPrice, quantityInStock) values (?,?,?,?,?,?)";

        try (
            // get connection to database
            Connection dbCon = DbConnection.getConnection(productUri);

            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);
          ) {
            // copy the data from the product domain object into the SQL parameters
            stmt.setString(1, product.getProductID());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getCategory());
            stmt.setBigDecimal(5, product.getListPrice());
            stmt.setBigDecimal(6, product.getQuantityInStock());

            stmt.executeUpdate();  // execute the statement

            } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Product searchProducts(String Id) {
        String sql = "select * from Product where productID = ?";

        try (
            // get connection to database
            Connection connection = DbConnection.getConnection(productUri);

            // create the statement
            PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
        // set the parameter
        stmt.setString(1, Id);

        // execute the query
        ResultSet rs = stmt.executeQuery();

        // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                String productID = rs.getString("productID");   
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal listPrice = rs.getBigDecimal("listPrice");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityInStock");

            return new Product(productID, name, description, category, listPrice, quantityInStock);

        } else {
            // no product matches given ID so return null
            return null;
        }

    } catch (SQLException ex) {  // we are forced to catch SQLException
        // don't let the SQLException leak from our DAO encapsulation
        throw new DAOException(ex.getMessage(), ex);
    }
    }
    
    
    
    
}
