/**
 * @author baihu868
 * INFO202 2019
 * Customer
 */
package domain;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

public class Customer {
    private Integer customerID;
    @NotNull(message = "Username must be provided.")
    @NotBlank(message = "Username must be provided.")
    @Length(min=6, message="Username must contain at least 6 characters.")
    private String username;
    @NotNull(message = "First name must be provided.")
    @NotBlank(message = "First name must be provided.")
    @Length(min=2, message="First name must contain at least 2 characters.")
    private String firstName;
    @NotNull(message = "Surname must be provided.")
    @NotBlank(message = "Surname must be provided.")
    @Length(min=2, message="Surname must contain at least 2 characters.")
    private String surName;
    @NotNull(message = "Password must be provided.")
    @NotBlank(message = "Password must be provided.")
    @Length(min=8, message="Password must contain at least 8 characters.")
    private String passWord;
    @NotNull(message = "Email address must be provided.")
    @NotBlank(message = "Email address must be provided.")
    private String emailAddress;
    @NotNull(message = "Shipping address must be provided.")
    @NotBlank(message = "Shipping address must be provided.")
    private String shippingAddress;

    public Customer(Integer customerID, String username, String firstName, String surName, String passWord, String emailAddress, String shippingAddress) {
        this.customerID = customerID;
        this.username = username;
        this.firstName = firstName;
        this.surName = surName;
        this.passWord = passWord;
        this.emailAddress = emailAddress;
        this.shippingAddress = shippingAddress;
    }

    public Customer() {
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", username=" + username + ", firstName=" + firstName + ", surName=" + surName + ", passWord=" + passWord + ", emailAddress=" + emailAddress + ", shippingAddress=" + shippingAddress + '}';
    }
    
    
}
