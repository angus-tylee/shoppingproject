/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.ProductDAOCollections.categories;
import static dao.ProductDAOCollections.products;
import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author angustylee
 */
public class ProductDAODatabase implements ProductDAO {

    private String database_url = DbConnection.getDefaultConnectionUri();

    public ProductDAODatabase() {
    }

    public ProductDAODatabase(String database_url) {
        this.database_url = database_url;
    }

    @Override
    public Collection<String> getCategories() {

        String sql = "select Category from Products";

        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(database_url);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            Collection<String> categories = new HashSet<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String category = rs.getString("Category");
                // and put it in the collection
                categories.add(category);
            }

            return categories;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public Product productSearch(String productIDs) {

        String sql = "select * from products where productid = ?";

        try (
                // get connection to database
                Connection connection = DbConnection.getConnection(database_url);
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // set the parameter
            stmt.setString(1, productIDs);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                String productID = rs.getString("ProductID");
                String name = rs.getString("FirstName");
                String description = rs.getString("Description");
                String category = rs.getString("Category");
                BigDecimal stockQuantity = rs.getBigDecimal("StockQuantity");
                BigDecimal listPrice = rs.getBigDecimal("ListPrice");

                return new Product(productID, name, description, category, stockQuantity, listPrice);

            } else {
                // no student matches given ID so return null
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void removeProduct(Product productID) {

        String sql = " delete from Products where ProductID = ?";

        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(database_url);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, productID.getProductID());

            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void saveProduct(Product aProduct) {

        String sql = "insert into products (ProductID, FirstName, Description, Category, StockQuantity, ListPrice) values (?,?,?,?,?,?)";

        try (
                // get connection to database
                Connection connection = DbConnection.getConnection(database_url);
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // copy the data from the student domain object into the SQL parameters
            stmt.setString(1, aProduct.getProductID());
            stmt.setString(2, aProduct.getName());
            stmt.setString(3, aProduct.getDescription());
            stmt.setString(4, aProduct.getCategory());
            stmt.setBigDecimal(5, aProduct.getStockQuantity());
            stmt.setBigDecimal(6, aProduct.getListPrice());

            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<Product> displayProducts() {

        String sql = "select * from Products order by PRODUCTID";

        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(database_url);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            List<Product> products = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String productID = rs.getString("ProductID");
                String name = rs.getString("FirstName");
                String description = rs.getString("Description");
                String category = rs.getString("Category");
                BigDecimal stockQuantity = rs.getBigDecimal("StockQuantity");
                BigDecimal listPrice = rs.getBigDecimal("ListPrice");

                // use the data to create a student object
                Product p = new Product(productID, name, description, category, stockQuantity, listPrice);

                // and put it in the collection
                products.add(p);
            }

            return products;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<Product> categoryFilter(String category) {

        String sql = "select * from products where category = ?";

        try (
                // get connection to database
                Connection connection = DbConnection.getConnection(database_url);
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // set the parameter
            stmt.setString(1, category);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            List<Product> products = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String productID = rs.getString("ProductID");
                String name = rs.getString("FirstName");
                String description = rs.getString("Description");
                String category1 = rs.getString("Category");
                BigDecimal stockQuantity = rs.getBigDecimal("StockQuantity");
                BigDecimal listPrice = rs.getBigDecimal("ListPrice");

                // use the data to create a student object
                Product p = new Product(productID, name, description, category1, stockQuantity, listPrice);

                // and put it in the collection
                products.add(p);
            }

            return products;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

}
