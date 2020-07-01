import dao.CustomerDAO;
import dao.CustomerDAOCollections;
import dao.CustomerDAODatabase;
import dao.ProductDAOCollections;
import gui.MainMenu;
import dao.ProductDAO;
import dao.ProductDAODatabase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tylan825
 */
public class Administration {

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAODatabase();
        MainMenu menu = new MainMenu(dao);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
}
