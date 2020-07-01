/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.util.Objects;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author tylan825
 */
public class Product {

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.productID);
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
    
    @NotNull(message = "productID must be provided.")
    @NotBlank(message = "productID must be provided.")
    @Length(min=3, message="productID must contain at least 3 numbers.") 
    private String productID;
    
    @NotNull(message = "Name must be provided.")
    @NotBlank(message = "Name must be provided.")
    @Length(min=2, message="Name must contain at least two characters.")    
    private String name;
    
    private String description;
    
    @NotNull(message = "Category must be provided.")
    @NotBlank(message = "Category must be provided.")
    @Length(min=2, message="Category must contain at least two characters.")
    private String category;
    
    @NotNull(message = "Quantity must be provided.")
    @NotBlank(message = "Quantity must be provided.")
    @NotNegative(message = "Quantity must be zero or greater.")
    private BigDecimal stockQuantity;
    
    @NotNull(message = "Price must be provided.")
    @NotBlank(message = "Price must be provided.")
    @NotNegative(message = "Price must be zero or greater.")
    private BigDecimal listPrice;

    public Product(String productID, String name,  String category,String description, BigDecimal stockQuantity, BigDecimal listPrice) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.listPrice = listPrice;
    }

    
    
    public Product() {
        
    }

    public Product(String category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public BigDecimal getListPrice() {
        return listPrice;
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

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public String toString() {
        return "productID=" + productID + ", name=" + name + ", description=" + description + ", category=" + category + ", stockQuantity=" + stockQuantity + ", listPrice=" + listPrice + '}';
    }
 
    
}
