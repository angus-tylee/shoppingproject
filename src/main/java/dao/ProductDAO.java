/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author tylan825
 */
public interface ProductDAO {

    Collection<Product> categoryFilter(String categoryIDs);

    Collection<Product> displayProducts();

    Collection<String> getCategories();

    Product productSearch(String productIDs);

    void removeProduct(Product product);

    void saveProduct(Product product);
    
}
