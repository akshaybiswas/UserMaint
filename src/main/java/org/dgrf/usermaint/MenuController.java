/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.usermaint;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Inject;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author dgrfiv
 */
@Named(value = "menuController")
@RequestScoped
public class MenuController {
    
    @Inject
    LoginController loginController;
    
    /**
     * Creates a new instance of MenuView
     */
    private MenuModel model;
    
    
    public MenuController() {
    }
    
    @PostConstruct
    void init() {
        model = new DefaultMenuModel();

        //First submenu
        DefaultSubMenu mainSubmenu = new DefaultSubMenu("Menu");
        DefaultMenuItem item;
        String menuUrl;
        
        item = new DefaultMenuItem("Home");
        menuUrl = "/loggedin/landing?faces-redirect=true";
        item.setOutcome(menuUrl);
        mainSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Users");           
        menuUrl = "/loggedin/UserMaintList?faces-redirect=true";
        item.setOutcome(menuUrl);
        mainSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Change Password");           
        menuUrl = "/loggedin/ChangeUserPassword?faces-redirect=true&userid=" + loginController.getUserId();
        item.setOutcome(menuUrl);
        mainSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Products");           
        menuUrl = "/loggedin/products?faces-redirect=true";
        item.setOutcome(menuUrl);
        mainSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Portfolio");           
        menuUrl = "/loggedin/portfolio?faces-redirect=true";
        item.setOutcome(menuUrl);
        mainSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Contact");           
        menuUrl = "/loggedin/contact?faces-redirect=true";
        item.setOutcome(menuUrl);
        mainSubmenu.addElement(item);

        model.addElement(mainSubmenu);
    }


    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
}
