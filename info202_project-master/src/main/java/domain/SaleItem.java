/**
 * @author baihu868
 * INFO202 2019
 * SaleItem
 */
package domain;

import java.math.BigDecimal;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

public class SaleItem {
    Product product = new Product();
    @NotNull(message = "Quantity purchased must be provided.")
    @NotBlank(message = "Quantity purchase must be provided.")
    private BigDecimal quantityPurchased;
    @NotNull(message = "Sale price must be provided.")
    @NotBlank(message = "Sale price must be provided.")
    private BigDecimal salePrice;
    

    public SaleItem(Product product, BigDecimal quantityPurchased) {
        this.product = product;
        this.quantityPurchased = quantityPurchased;
    }

    SaleItem() {
    }

    public BigDecimal getQuantityPurchased() {
        return quantityPurchased;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public Product getProduct() {
        return product;
    }
    
    
    
    
    public BigDecimal getItemTotal() {
        return(quantityPurchased.multiply(salePrice));
    }

    public void setQuantityPurchased(BigDecimal quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public String toString() {
        return "SaleItem{" + "quantityPurchased=" + quantityPurchased + ", salePrice=" + salePrice + '}';
    }
    
    
}
