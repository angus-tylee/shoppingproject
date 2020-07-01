/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.ProductDAO;
import dao.SaleDAO;
import domain.Customer;
import domain.Sale;
import org.jooby.Status;
import org.jooby.Jooby;

/**
 *
 * @author angustylee
 */
public class SaleModule extends Jooby {
    
    public SaleModule(SaleDAO dao) {
    
        post("/api/sales", (req, rsp) -> {
            Sale sale = req.body().to(Sale.class);
            dao.save(sale);
            rsp.status(Status.CREATED);
        });
        
}

    


