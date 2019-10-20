/**
 * @author baihu868
 * INFO202 2019
 * Product.java
 */
package domain;

import java.math.BigDecimal;
import java.util.Objects;
import net.sf.oval.constraint.*;

public class Product {
    @NotNull(message = "Product ID must be provided.")
    @NotBlank(message = "Product ID must be provided.")
    @NotNegative(message = "Product ID must be zero or greater.")
    private String productID;
    @NotNull(message = "Name must be provided.")
    @NotBlank(message = "Name must be provided.")
    @Length(min=2, message="Name must contain at least two characters.")
    private String name;
    @NotNull(message = "Description must be provided.")
    @NotBlank(message = "Description must be provided.")
    private String description;
    @NotNull(message = "Category must be provided.")
    @NotBlank(message = "Category must be provided.")
    @Length(min=2, message="Category must contain at least two characters.")
    private String category;
    @NotNull(message = "Price must be provided.")
    @NotNegative(message = "Price must be zero or greater.")
    private BigDecimal listPrice;
    @NotNull(message = "Quantity in stock must be provided.")
    @NotNegative(message = "Quantity in stock must be zero or greater.")
    private BigDecimal quantityInStock;
    
    public Product(String id, String name, String description, String category, BigDecimal listPrice, BigDecimal quantityInStock) {
        this.productID = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.listPrice = listPrice;
        this.quantityInStock = quantityInStock;    
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.productID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.productID, other.productID)) {
            return false;
        }
        return true;
    }

    public Product() {
    }
        
    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public BigDecimal getQuantityInStock() {
        return quantityInStock;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public void setQuantityInStock(BigDecimal quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Product ID = " + productID + ", Name = " + name + ", Category = " + category;
    }
      
}
