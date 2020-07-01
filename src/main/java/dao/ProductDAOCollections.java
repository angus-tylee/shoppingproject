/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author angustylee
 */
public class ProductDAOCollections implements ProductDAO {
    
    public static Collection<Product> products = new HashSet<>();
    public static Collection<String> categories = new HashSet<>();
   public static Map<String, Product> productID = new HashMap<>();
   private static final Multimap<String, Product> categoryID = HashMultimap.create();
    
    @Override
    public void saveProduct(Product product){
        products.add(product);
        categories.add(product.getCategory());
        productID.put(product.getProductID(),product);
        categoryID.put(product.getCategory(),product);
    }
    
    @Override
    public Collection<Product> displayProducts(){
        return products;
    }
    
    @Override
    public Product productSearch(String productIDs){
        Product searchresult = productID.get(productIDs);
        return searchresult;
       }
    
    @Override
    public Collection<Product> categoryFilter(String categoryIDs){
        return categoryID.get(categoryIDs);
       }
   
    
    @Override
    public void removeProduct(Product product){
        categories.remove(product.getCategory());
        productID.remove(product.getProductID());
        categoryID.remove(product.getCategory(), product);
        products.remove(product);
    }
  
    
    @Override
    public Collection<String> getCategories(){
        return categories;
    }
}
