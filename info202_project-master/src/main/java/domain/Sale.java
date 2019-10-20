/**
 * @author baihu868
 * INFO202 2019
 * Sale
 */
package domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

public class Sale {
    private Integer saleID;
    @NotNull(message = "Date must be provided.")
    @NotBlank(message = "Date must be provided.")
    private LocalDate date;
    @NotNull(message = "Status must be provided.")
    @NotBlank(message = "Status must be provided.")
    private String status;
    SaleItem saleItem = new SaleItem();
    Customer customer = new Customer();
    ArrayList <SaleItem> items = new ArrayList<>();
  
    public Sale(Integer saleID, LocalDate date, String status) {
        this.saleID = saleID;
        this.date = date;
        this.status = status;
    }

    public Integer getSaleID() {
        return saleID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
    
    public BigDecimal getTotal() {
        return saleItem.getItemTotal();
    }

    public ArrayList<SaleItem> getItems() {
        return items;
    }

    public SaleItem getSaleItem() {
        return saleItem;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    
    public void addItem(SaleItem saleItem){
        items.add(saleItem);
    }

    public void setSaleID(Integer saleID) {
        this.saleID = saleID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Sale{" + "saleID=" + saleID + ", date=" + date + ", status=" + status + '}';
    } 
}
