/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import org.jooby.Jooby;
import dao.ProductDAO;

/**
 *
 * @author tylan825
 */
public class ProductModule extends Jooby {

    public ProductModule(ProductDAO dao) {
        
        get("/api/products", () -> dao.displayProducts());
        
        get("/api/products/:id", (req) -> {
            String id = req.param("id").value(); 
        return dao.productSearch(id);
        });
        
        get("/api/categories", () -> dao.getCategories());
        
        get("/api/categories/:category", (req) -> {
            String category = req.param("category").value(); 
        return dao.categoryFilter(category);
        });
        
}
}
